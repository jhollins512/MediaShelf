package com.jamaalhollins.movieshelf.feature.mediaDetails.presentation

import androidx.lifecycle.ViewModel
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetMovieDetailsUseCase
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetTVShowDetailsUseCase

class MovieDetailViewModel(
    private val getMovieDetails: GetMovieDetailsUseCase,
) : ViewModel() {
}