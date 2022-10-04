package com.jamaalhollins.movieshelf.feature.mediaDetails.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.elevation.ElevationOverlayProvider
import com.jamaalhollins.movieshelf.core.data.api.ApiConstants
import com.jamaalhollins.movieshelf.core.extensions.setImage
import com.jamaalhollins.movieshelf.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailViewModel.getMovieDetails(args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        subscribeUi()
    }

    private fun setupToolbar() {
        setupBackButtonNavigation()
        checkDarkModeToolbar()
    }

    private fun setupBackButtonNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun checkDarkModeToolbar() {
        val context = requireContext()

        when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.movieDetailsCollapsingToolbarLayout.setContentScrimColor(
                    ElevationOverlayProvider(context)
                        .compositeOverlayWithThemeSurfaceColorIfNeeded(12f)
                )
            }
        }
    }

    private fun subscribeUi() {
        movieDetailViewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.backdropImage.setImage(
                ApiConstants.IMAGE_BASE_URL_W780 + it.backdropPath,
                null
            )

            binding.posterImage.setImage(ApiConstants.IMAGE_BASE_URL_W500 + it.posterPath, null)
        }

//        val params =
//            (binding.movieDetailsContentScrollView.layoutParams as CoordinatorLayout.LayoutParams)
//        val behavior = params.behavior as ScrollingViewBehavior
//        behavior.overlayTop = 50.dpToPx()
    }
}