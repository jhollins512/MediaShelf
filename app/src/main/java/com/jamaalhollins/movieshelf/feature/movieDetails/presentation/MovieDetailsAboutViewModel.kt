package com.jamaalhollins.movieshelf.feature.movieDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.model.Credits
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieContentRatingUseCase
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieCreditsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class MovieDetailsAboutViewModel(
    private val getMovieCredits: GetMovieCreditsUseCase,
    private val getMovieContentRating: GetMovieContentRatingUseCase
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

    fun loadContentRating(movieId: Int) {
        viewModelScope.launch {
            val contentRating = getMovieContentRating(movieId, Locale.getDefault())
            _uiState.update {
                it.copy(
                    isLoadingContentRating = false,
                    contentRating = contentRating
                )
            }
        }
    }
}

data class MovieDetailsAboutUiState(
    val isLoadingCredits: Boolean = true,
    val isLoadingContentRating: Boolean = true,
    val credits: Credits? = null,
    val contentRating: String = ""
)