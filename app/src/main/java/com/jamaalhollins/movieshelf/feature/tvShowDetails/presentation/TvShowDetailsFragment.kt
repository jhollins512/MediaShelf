package com.jamaalhollins.movieshelf.feature.tvShowDetails.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.jamaalhollins.movieshelf.core.domain.model.TVShowDetails
import com.jamaalhollins.movieshelf.core.extensions.navigateToExternalUrl
import com.jamaalhollins.movieshelf.core.utils.getScreenWidth
import com.jamaalhollins.movieshelf.core.utils.isDarkModeEnabled
import com.jamaalhollins.movieshelf.databinding.FragmentTvShowDetailsBinding
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
        setupPosterPosition()
        setupScrollView()
        subscribeUi()
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

    private fun setupPosterPosition() {
        val marginY = (.10 * getScreenWidth(requireActivity())).toInt()
        binding.posterImage.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            this.topMargin = -marginY
        }
    }

    private fun setupScrollView() {
        binding.tvShowDetailsContentScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val offset = binding.posterImage.y - getActionBarSize()

            if (scrollY >= offset) {
                if (isDarkModeEnabled(requireContext())) {
                    binding.tvShowDetailsToolbar.setBackgroundColor(
                        ElevationOverlayProvider(requireContext()).compositeOverlayWithThemeSurfaceColorIfNeeded(
                            12f
                        )
                    )
                } else {
                    binding.tvShowDetailsToolbar.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.primaryColor
                        )
                    )
                }
            } else {
                binding.tvShowDetailsToolbar.setBackgroundColor(
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
                    else -> {}
                }
            }
        }
    }

    private fun navigateToMediaDetails(media: Media) {
        findNavController().navigate("movieshelf://tv/${media.id}".toUri())
    }

    private fun navigateToMovieDetailsAbout(tvShowDetails: TVShowDetails) {
        findNavController().navigate(
            TvShowDetailsFragmentDirections.actionTvShowDetailsAbout(
                tvShowDetails
            )
        )
    }
}