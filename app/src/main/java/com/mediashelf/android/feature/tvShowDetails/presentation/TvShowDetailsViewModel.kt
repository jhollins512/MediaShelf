package com.mediashelf.android.feature.tvShowDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediashelf.android.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.model.TVShowDetails
import com.mediashelf.android.core.domain.model.WatchProviderWithViewingOptions
import com.mediashelf.android.core.extensions.createExceptionHandler
import com.mediashelf.android.feature.tvShowDetails.domain.GetTVShowDetailsUseCase
import com.mediashelf.android.feature.tvShowDetails.domain.GetTVShowRecommendationsUseCase
import com.mediashelf.android.feature.tvShowDetails.domain.GetTVShowWatchProvidersForLocaleUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class TvShowDetailsViewModel(
    private val getTvShowDetails: GetTVShowDetailsUseCase,
    private val getTvShowRecommendations: GetTVShowRecommendationsUseCase,
    private val getTvShowWatchProvidersForLocale: GetTVShowWatchProvidersForLocaleUseCase,
    private val formatWatchProvidersWithType: FormatToWatchProviderWithViewingOptionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvShowDetailsUiState())
    val uiState: StateFlow<TvShowDetailsUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<TvShowDetailsEffect>()
    val uiEffect: SharedFlow<TvShowDetailsEffect> = _uiEffect

    fun loadTVShowDetailsScreen(tvId: Int) {
        _uiState.update { it.copy(tvShowDetailsLoading = true, tvShowDetailsLoadFailure = false) }

        loadTvShowDetails(tvId)
        loadTvShowWatchProviders(tvId, Locale.getDefault())
        loadSimilarTvShows(tvId)
    }

    fun loadTvShowDetails(tvId: Int) {
        val exceptionHandler = viewModelScope.createExceptionHandler("Failed to movie details.") {
            _uiState.update {
                it.copy(
                    tvShowDetailsLoading = false,
                    tvShowDetailsLoadFailure = true
                )
            }
        }

        viewModelScope.launch(exceptionHandler) {
            val tvShowDetails = getTvShowDetails.invoke(tvId)

            _uiState.update {
                it.copy(
                    tvShowDetailsLoading = false,
                    tvShowDetailsLoadFailure = false,
                    tvShowDetails = tvShowDetails
                )
            }
        }
    }

    fun loadSimilarTvShows(tvId: Int) {
        val exceptionHandler =
            viewModelScope.createExceptionHandler("Failed to load similar tv shows.")

        viewModelScope.launch(exceptionHandler) {
            val similarTVShows = getTvShowRecommendations.invoke(tvId)

            _uiState.update { it.copy(similarTVShows = similarTVShows) }
        }
    }

    fun loadTvShowWatchProviders(tvId: Int, locale: Locale) {
        val exceptionHandler =
            viewModelScope.createExceptionHandler("Failed to load tv show watch providers.")

        viewModelScope.launch(exceptionHandler) {
            val watchProviderCountry = getTvShowWatchProvidersForLocale.invoke(tvId, locale)
            val tvShowWatchProviders = formatWatchProvidersWithType.invoke(watchProviderCountry)

            _uiState.update { it.copy(tvShowWatchProviders = tvShowWatchProviders) }
        }
    }

    fun showHomepage() {
        viewModelScope.launch {
            _uiState.value.tvShowDetails?.homepage?.let {
                _uiEffect.emit(TvShowDetailsEffect.NavigateToWatchNowLink(it))
            }
        }
    }

    fun navigateToMediaDetails(media: Media) {
        viewModelScope.launch {
            _uiEffect.emit(TvShowDetailsEffect.NavigateToTvShowDetails(media))
        }
    }

    fun navigateToTVShowDetailsAbout() {
        viewModelScope.launch {
            _uiState.value.tvShowDetails?.let {
                _uiEffect.emit(TvShowDetailsEffect.NavigateToTvShowDetailsAbout(it))
            }
        }
    }

    fun navigateToWatchProviderAttribution() {
        viewModelScope.launch {
            _uiState.value.tvShowDetails?.let {
                _uiEffect.emit(TvShowDetailsEffect.NavigateWatchProviderAttribution(it))
            }
        }
    }
}

data class TvShowDetailsUiState(
    val tvShowDetailsLoading: Boolean = true,
    val tvShowDetails: TVShowDetails? = null,
    val similarTVShows: List<Media> = emptyList(),
    val tvShowWatchProviders: List<WatchProviderWithViewingOptions> = emptyList(),
    val tvShowDetailsLoadFailure: Boolean = false
)

sealed class TvShowDetailsEffect {
    class NavigateToTvShowDetailsAbout(val tvShowDetails: TVShowDetails) : TvShowDetailsEffect()
    class NavigateToWatchNowLink(val link: String) : TvShowDetailsEffect()
    class NavigateToTvShowDetails(val media: Media) : TvShowDetailsEffect()
    class NavigateWatchProviderAttribution(val tvShowDetails: TVShowDetails) : TvShowDetailsEffect()
}