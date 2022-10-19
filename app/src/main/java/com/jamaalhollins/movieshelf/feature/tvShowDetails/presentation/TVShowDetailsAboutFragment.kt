package com.jamaalhollins.movieshelf.feature.tvShowDetails.presentation

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
import com.jamaalhollins.movieshelf.core.extensions.dpToPx
import com.jamaalhollins.movieshelf.core.presentation.MarginItemDecoration
import com.jamaalhollins.movieshelf.core.presentation.adapter.GenresAdapter
import com.jamaalhollins.movieshelf.databinding.FragmentTvShowDetailsAboutBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TVShowDetailsAboutFragment : Fragment() {

    private var _binding: FragmentTvShowDetailsAboutBinding? = null
    private val binding get() = _binding!!

    private val args: TVShowDetailsAboutFragmentArgs by navArgs()
    private val tvShowDetailsAboutViewModel: TVShowDetailsAboutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(args.tvShowDetails.id) {
            tvShowDetailsAboutViewModel.loadCredits(this)
            tvShowDetailsAboutViewModel.loadContentRating(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowDetailsAboutBinding.inflate(inflater, container, false)
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
        binding.toolbar.title = args.tvShowDetails.name

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupOverviewSection() {
        binding.overviewText.text = args.tvShowDetails.overview
    }

    private fun setupGenreList() {
        val genresAdapter = GenresAdapter()

        binding.genresList.apply {
            adapter = genresAdapter
            layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)
            addItemDecoration(MarginItemDecoration(top = 8.dpToPx(), end = 8.dpToPx()))
        }

        genresAdapter.submitList(args.tvShowDetails.genres)
    }

    private fun setupDetailsSection() {
        with(args.tvShowDetails) {
            binding.episodeCountText.text = numberOfEpisodes.toString()
            binding.releasedText.text = firstAirDate
            binding.originalLanguageText.text =
                Locale(originalLanguage).displayLanguage
        }
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            launch {
                tvShowDetailsAboutViewModel.uiState.flowWithLifecycle(
                    viewLifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).map { it.credits }.distinctUntilChanged().collectLatest {
                    binding.starringText.text =
                        it?.cast?.take(10)?.joinToString { it.name }
                }
            }

            launch {
                tvShowDetailsAboutViewModel.uiState.flowWithLifecycle(
                    viewLifecycleOwner.lifecycle,
                    Lifecycle.State.STARTED
                ).map { it.contentRating }.distinctUntilChanged().collectLatest {
                    binding.contentRatingText.text = it
                }
            }
        }
    }
}