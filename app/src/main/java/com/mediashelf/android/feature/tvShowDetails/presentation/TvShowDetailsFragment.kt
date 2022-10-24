package com.mediashelf.android.feature.tvShowDetails.presentation

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
import com.mediashelf.android.core.domain.model.TVShowDetails
import com.mediashelf.android.core.extensions.navigate
import com.mediashelf.android.core.extensions.navigateToExternalUrl
import com.mediashelf.android.core.extensions.navigateToExternalUrlWithCustomTabs
import com.mediashelf.android.core.navigation.NavigationRouter
import com.mediashelf.android.core.presentation.helper.setToolbarTransparent
import com.mediashelf.android.core.presentation.helper.setupPosterPosition
import com.mediashelf.android.core.presentation.helper.setupScrollView
import com.mediashelf.android.databinding.FragmentTvShowDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TvShowDetailsFragment : Fragment() {

    private val tvShowDetailsViewModel: TvShowDetailsViewModel by viewModel()

    private var _binding: FragmentTvShowDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: TvShowDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvShowDetailsViewModel.loadTvShowDetails(args.tvShowId)
        tvShowDetailsViewModel.loadTvShowWatchProviders(args.tvShowId, Locale.getDefault())
        tvShowDetailsViewModel.loadSimilarTvShows(args.tvShowId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            tvShowDetailsViewModel = this@TvShowDetailsFragment.tvShowDetailsViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupPosterPosition(requireActivity(), binding.posterImage)
        setupScrollView(
            requireActivity(),
            binding.tvShowDetailsContentScrollView,
            binding.tvShowDetailsToolbar,
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
        binding.tvShowDetailsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            tvShowDetailsViewModel.uiState.map { it.tvShowDetailsLoadFailure }
                .distinctUntilChanged()
                .collectLatest {
                    if (it) {
                        handleTVShowDetailsError()
                    } else {
                        setToolbarTransparent(requireContext(), binding.tvShowDetailsToolbar, true)
                    }
                }
        }
    }

    private fun subscribeEffects() {
        lifecycleScope.launch {
            tvShowDetailsViewModel.uiEffect.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collect {
                when (it) {
                    is TvShowDetailsEffect.NavigateToWatchNowLink -> it.link.navigateToExternalUrl(
                        requireContext()
                    )

                    is TvShowDetailsEffect.NavigateToTvShowDetails -> navigateToMediaDetails(it.media)

                    is TvShowDetailsEffect.NavigateToTvShowDetailsAbout -> navigateToMovieDetailsAbout(
                        it.tvShowDetails
                    )
                    is TvShowDetailsEffect.NavigateWatchProviderAttribution -> navigateToWatchProviderAttribution(
                        it.tvShowDetails
                    )
                }
            }
        }
    }

    private fun navigateToMediaDetails(media: Media) {
        findNavController().navigate(NavigationRouter.MediaRouter(media))
    }

    private fun navigateToMovieDetailsAbout(tvShowDetails: TVShowDetails) {
        findNavController().navigate(
            TvShowDetailsFragmentDirections.actionTvShowDetailsAbout(
                tvShowDetails
            )
        )
    }

    private fun navigateToWatchProviderAttribution(tvShowDetails: TVShowDetails) {
        getString(
            R.string.tmdb_tv_show_base_url,
            tvShowDetails.id,
            tvShowDetails.name.replace(" ", "-")
        ).navigateToExternalUrlWithCustomTabs(requireContext())
    }

    private fun handleTVShowDetailsError() {
        Snackbar.make(
            binding.root,
            getString(R.string.label_error_occured),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.btn_try_again) {
            tvShowDetailsViewModel.loadTVShowDetailsScreen(args.tvShowId)
        }.show()

        setToolbarTransparent(requireContext(), binding.tvShowDetailsToolbar, false)
    }
}