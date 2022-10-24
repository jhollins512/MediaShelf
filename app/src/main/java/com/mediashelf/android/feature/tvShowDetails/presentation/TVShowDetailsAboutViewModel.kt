package com.mediashelf.android.feature.tvShowDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediashelf.android.core.domain.model.Credits
import com.mediashelf.android.core.extensions.createExceptionHandler
import com.mediashelf.android.feature.tvShowDetails.domain.GetTVShowContentRatingUseCase
import com.mediashelf.android.feature.tvShowDetails.domain.GetTVShowCreditsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class TVShowDetailsAboutViewModel(
    private val getTVShowCredits: GetTVShowCreditsUseCase,
    private val getTVShowContentRating: GetTVShowContentRatingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TVShowDetailsAboutUiState())
    val uiState: StateFlow<TVShowDetailsAboutUiState> = _uiState

    fun loadCredits(tvId: Int) {
        val exceptionHandler = viewModelScope.createExceptionHandler("Loading credits failed") {
            _uiState.update { it.copy(isLoadingCredits = false) }
        }

        viewModelScope.launch(exceptionHandler) {
            val credits = getTVShowCredits(tvId)
            _uiState.update { it.copy(isLoadingCredits = false, credits = credits) }
        }
    }

    fun loadContentRating(tvId: Int) {
        val exceptionHandler =
            viewModelScope.createExceptionHandler("Loading content rating failed") {
                _uiState.update { it.copy(isLoadingContentRating = false) }
            }

        viewModelScope.launch(exceptionHandler) {
            val contentRating = getTVShowContentRating(tvId, Locale.getDefault())
            _uiState.update {
                it.copy(
                    isLoadingContentRating = false,
                    contentRating = contentRating
                )
            }
        }
    }
}

data class TVShowDetailsAboutUiState(
    val isLoadingCredits: Boolean = true,
    val isLoadingContentRating: Boolean = true,
    val credits: Credits? = null,
    val contentRating: String = ""
)