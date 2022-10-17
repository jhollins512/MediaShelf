package com.jamaalhollins.movieshelf.feature.movieDetails.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.elevation.ElevationOverlayProvider
import com.jamaalhollins.movieshelf.R
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails
import com.jamaalhollins.movieshelf.core.extensions.navigateToExternalUrl
import com.jamaalhollins.movieshelf.core.utils.getScreenWidth
import com.jamaalhollins.movieshelf.core.utils.isDarkModeEnabled
import com.jamaalhollins.movieshelf.databinding.FragmentMovieDetailsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailsViewModel.loadMovieDetails(args.movieId)
        movieDetailsViewModel.loadMovieWatchProviders(args.movieId, Locale.getDefault())
        movieDetailsViewModel.loadSimilarMovies(args.movieId)
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
        setupPosterPosition()
        setupScrollView()
        subscribeUi()
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

    private fun setupPosterPosition() {
        val marginY = (.10 * getScreenWidth(requireActivity())).toInt()
        binding.posterImage.updateLayoutParams<MarginLayoutParams> {
            this.topMargin = -marginY
        }
    }

    private fun setupScrollView() {
        binding.movieDetailsContentScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val offset = binding.posterImage.y - getActionBarSize()

            if (scrollY >= offset) {
                if (isDarkModeEnabled(requireContext())) {
                    binding.movieDetailsToolbar.setBackgroundColor(
                        ElevationOverlayProvider(requireContext()).compositeOverlayWithThemeSurfaceColorIfNeeded(
                            12f
                        )
                    )
                } else {
                    binding.movieDetailsToolbar.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.primaryColor
                        )
                    )
                }
            } else {
                binding.movieDetailsToolbar.setBackgroundColor(
                    Color.TRANSPARENT
                )
            }
        }
    }

    private fun getActionBarSize(): Int {
        val typedValue = TypedValue()

        return if (requireActivity().theme.resolveAttribute(
                androidx.appcompat.R.attr.actionBarSize, typedValue, true
            )
        ) {
            TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
        } else {
            0
        }
    }

    private fun subscribeUi() {
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
                }
            }
        }
    }

    private fun navigateToMediaDetails(media: Media) {
        findNavController().navigate("movieshelf://movie/${media.id}".toUri())
    }

    private fun navigateToMovieDetailsAbout(movieDetails: MovieDetails) {
        findNavController().navigate(MovieDetailsFragmentDirections.actionMovieAbout(movieDetails))
    }
}