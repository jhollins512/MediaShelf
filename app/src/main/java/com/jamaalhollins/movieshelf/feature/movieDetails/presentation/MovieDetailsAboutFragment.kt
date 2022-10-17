package com.jamaalhollins.movieshelf.feature.movieDetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.jamaalhollins.movieshelf.core.extensions.dpToPx
import com.jamaalhollins.movieshelf.core.presentation.MarginItemDecoration
import com.jamaalhollins.movieshelf.core.presentation.adapter.GenresAdapter
import com.jamaalhollins.movieshelf.databinding.FragmentMovieDetailsAboutBinding

class MovieDetailsAboutFragment : Fragment() {

    private var _binding: FragmentMovieDetailsAboutBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailsAboutFragmentArgs by navArgs()

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

        with(args.movieDetails) {
            binding.movieAboutLayout.toolbar.title = title
            binding.movieAboutLayout.overviewText.text = overview
        }

        setupToolbar()
        setupGenreList()
    }

    private fun setupGenreList() {
        val genresAdapter = GenresAdapter()

        binding.movieAboutLayout.genresList.apply {
            adapter = genresAdapter
            layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)
            addItemDecoration(MarginItemDecoration(end = 8.dpToPx()))
        }

        genresAdapter.submitList(args.movieDetails.genres)
    }

    private fun setupToolbar() {
        binding.movieAboutLayout.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}