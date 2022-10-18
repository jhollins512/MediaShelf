package com.jamaalhollins.movieshelf.feature.movieDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.model.Credits
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieCreditsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsAboutViewModel(
    private val getMovieCredits: GetMovieCreditsUseCase,
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailsAboutUiState())
    val uiState: StateFlow<MovieDetailsAboutUiState> = _uiState

    fun loadCredits(movieId: Int) {
        viewModelScope.launch {
            val credits = getMovieCredits(movieId)
            _uiState.update { it.copy(isLoadingCredits = false, credits = credits) }
        }
    }
}

data class MovieDetailsAboutUiState(
    val isLoadingCredits: Boolean = true,
    val credits: Credits? = null
)