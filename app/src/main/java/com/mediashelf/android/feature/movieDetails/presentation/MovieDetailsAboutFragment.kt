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
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.mediashelf.android.R
import com.mediashelf.android.core.extensions.dpToPx
import com.mediashelf.android.core.presentation.MarginItemDecoration
import com.mediashelf.android.core.presentation.adapter.GenresAdapter
import com.mediashelf.android.databinding.FragmentMovieDetailsAboutBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MovieDetailsAboutFragment : Fragment() {

    private var _binding: FragmentMovieDetailsAboutBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsAboutViewModel: MovieDetailsAboutViewModel by viewModel()
    private val args: MovieDetailsAboutFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(args.movieDetails.id) {
            movieDetailsAboutViewModel.loadCredits(this)
            movieDetailsAboutViewModel.loadContentRating(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupOverviewSection()
        setupGenreList()
        setupDetailsSection()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        binding.toolbar.title = args.movieDetails.title

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupOverviewSection() {
        binding.overviewText.text = args.movieDetails.overview
    }

    private fun setupGenreList() {
        val genresAdapter = GenresAdapter()

        binding.genresList.apply {
            adapter = genresAdapter
            layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)
            addItemDecoration(MarginItemDecoration(top = 8.dpToPx(), end = 8.dpToPx()))
        }

        genresAdapter.submitList(args.movieDetails.genres)
    }

    private fun setupDetailsSection() {
        with(args.movieDetails) {
            binding.durationText.text = getString(R.string.format_minutes, runtime)
            binding.releasedText.text = releaseDate
            binding.originalLanguageText.text =
                Locale(originalLanguage).displayLanguage
        }
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            launch {
                movieDetailsAboutViewModel.uiState.flowWithLifecycle(
                    viewLifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).map { it.credits }.distinctUntilChanged().collectLatest {
                    binding.starringText.text =
                        it?.cast?.take(10)?.joinToString { it.name }
                    binding.directorsText.text = it?.getDirectorName()
                }
            }

            launch {
                movieDetailsAboutViewModel.uiState.flowWithLifecycle(
                    viewLifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).map { it.contentRating }.distinctUntilChanged().collectLatest {
                    binding.contentRatingText.text = it
                }
            }
        }
    }
}