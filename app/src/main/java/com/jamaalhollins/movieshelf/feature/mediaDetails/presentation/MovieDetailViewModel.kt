package com.jamaalhollins.movieshelf.feature.mediaDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetails: GetMovieDetailsUseCase,
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = getMovieDetails.invoke(movieId)
        }
    }
}