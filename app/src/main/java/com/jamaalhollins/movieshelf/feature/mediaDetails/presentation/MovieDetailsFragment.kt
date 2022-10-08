package com.jamaalhollins.movieshelf.feature.mediaDetails.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.elevation.ElevationOverlayProvider
import com.jamaalhollins.movieshelf.R
import com.jamaalhollins.movieshelf.core.data.api.ApiConstants
import com.jamaalhollins.movieshelf.core.extensions.setImage
import com.jamaalhollins.movieshelf.core.utils.getScreenWidth
import com.jamaalhollins.movieshelf.core.utils.isDarkModeEnabled
import com.jamaalhollins.movieshelf.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailsViewModel.loadMovieDetails(args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

    private fun setupToolbar() {
        binding.movieDetailsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupPosterPosition() {
        val posterTranslationY = .10f * getScreenWidth(requireActivity())
        binding.posterImage.translationY = -posterTranslationY
    }

    private fun setupScrollView() {
        binding.movieDetailsContentScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            val offset =
                binding.posterImage.y - getActionBarSize()

            if (scrollY >= offset) {
                if (isDarkModeEnabled(requireContext())) {
                    binding.movieDetailsToolbar.setBackgroundColor(
                        ElevationOverlayProvider(requireContext())
                            .compositeOverlayWithThemeSurfaceColorIfNeeded(12f)
                    )
                } else {
                    binding.movieDetailsToolbar.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.primaryColor
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
                androidx.appcompat.R.attr.actionBarSize,
                typedValue,
                true
            )
        ) {
            TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
        } else {
            0
        }
    }

    private fun subscribeUi() {
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.backdropImage.setImage(
                ApiConstants.IMAGE_BASE_URL_W780 + it.backdropPath,
                null
            )

            binding.posterImage.setImage(ApiConstants.IMAGE_BASE_URL_W500 + it.posterPath, null)
        }
    }
}