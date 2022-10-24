package com.mediashelf.android.feature.movieDetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mediashelf.android.R
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.model.MovieDetails
import com.mediashelf.android.core.extensions.navigate
import com.mediashelf.android.core.extensions.navigateToExternalUrl
import com.mediashelf.android.core.extensions.navigateToExternalUrlWithCustomTabs
import com.mediashelf.android.core.navigation.NavigationRouter
import com.mediashelf.android.core.presentation.helper.setToolbarTransparent
import com.mediashelf.android.core.presentation.helper.setupPosterPosition
import com.mediashelf.android.core.presentation.helper.setupScrollView
import com.mediashelf.android.databinding.FragmentMovieDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailsViewModel.loadMovieDetailsScreen(args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            movieDetailsViewModel = this@MovieDetailsFragment.movieDetailsViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupPosterPosition(requireActivity(), binding.posterImage)
        setupScrollView(
            requireActivity(),
            binding.movieDetailsContentScrollView,
            binding.movieDetailsToolbar,
            binding.posterImage
        )
        subscribeUi()
        subscribeEffects()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        binding.movieDetailsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            movieDetailsViewModel.uiState.map { it.movieDetailsLoadFailure }.distinctUntilChanged()
                .collectLatest {
                    if (it) {
                        handleMovieDetailsError()
                    } else {
                        setToolbarTransparent(requireContext(), binding.movieDetailsToolbar, true)
                    }
                }
        }
    }

    private fun subscribeEffects() {
        lifecycleScope.launch {
            movieDetailsViewModel.uiEffect.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collect {
                when (it) {
                    is MovieDetailsEffect.NavigateToWatchNowLink -> it.link.navigateToExternalUrl(
                        requireContext()
                    )

                    is MovieDetailsEffect.NavigateToMovieDetails -> navigateToMediaDetails(it.media)
                    is MovieDetailsEffect.NavigateToMovieDetailsAbout -> navigateToMovieDetailsAbout(
                        it.movieDetails
                    )
                    is MovieDetailsEffect.NavigateWatchProviderAttribution -> navigateToWatchProviderAttribution(
                        it.movieDetails
                    )
                }
            }
        }
    }

    private fun navigateToWatchProviderAttribution(movieDetails: MovieDetails) {
        getString(
            R.string.tmdb_movie_base_url,
            movieDetails.id,
            movieDetails.title.replace(" ", "-")
        ).navigateToExternalUrlWithCustomTabs(requireContext())
    }

    private fun navigateToMediaDetails(media: Media) {
        findNavController().navigate(NavigationRouter.MediaRouter(media))
    }

    private fun navigateToMovieDetailsAbout(movieDetails: MovieDetails) {
        findNavController().navigate(
            MovieDetailsFragmentDirections.actionMovieDetailsAbout(
                movieDetails
            )
        )
    }

    private fun handleMovieDetailsError() {
        Snackbar.make(
            binding.root,
            getString(R.string.label_error_occured),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.btn_try_again) {
            movieDetailsViewModel.loadMovieDetailsScreen(args.movieId)
        }.show()

        setToolbarTransparent(requireContext(), binding.movieDetailsToolbar, false)
    }
}