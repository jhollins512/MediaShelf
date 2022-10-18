package com.jamaalhollins.movieshelf.feature.tvShowDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.model.TVShowDetails
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderWithViewingOptions
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.GetTVShowDetailsUseCase
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.GetTVShowRecommendationsUseCase
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.GetTVShowWatchProvidersForLocaleUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.*

class TvShowDetailsViewModel(
    private val getTvShowDetails: GetTVShowDetailsUseCase,
    private val getTvShowRecommendations: GetTVShowRecommendationsUseCase,
    private val getTvShowWatchProvidersForLocale: GetTVShowWatchProvidersForLocaleUseCase,
    private val formatWatchProvidersWithType: FormatToWatchProviderWithViewingOptionsUseCase
) : ViewModel() {

    private val _tvShowDetails = MutableLiveData<TVShowDetails>()
    val tvShowDetails: LiveData<TVShowDetails> = _tvShowDetails

    private val _similarTvShows = MutableLiveData<List<Media>>()
    val similarTvShows: LiveData<List<Media>> = _similarTvShows

    private val _tvShowWatchProviders =
        MutableLiveData<List<WatchProviderWithViewingOptions>>()
    val tvShowWatchProviders: LiveData<List<WatchProviderWithViewingOptions>> =
        _tvShowWatchProviders

    private val _uiEffect = MutableSharedFlow<TvShowDetailsEffect>()
    val uiEffect: SharedFlow<TvShowDetailsEffect> = _uiEffect

    fun loadTvShowDetails(movieId: Int) {
        viewModelScope.launch {
            _tvShowDetails.value = getTvShowDetails.invoke(movieId)
        }
    }

    fun loadSimilarTvShows(tvId: Int) {
        viewModelScope.launch {
            _similarTvShows.value = getTvShowRecommendations.invoke(tvId)
        }
    }

    fun loadTvShowWatchProviders(tvId: Int, locale: Locale) {
        viewModelScope.launch {
            val watchProviderCountry = getTvShowWatchProvidersForLocale.invoke(tvId, locale)
            _tvShowWatchProviders.value =
                formatWatchProvidersWithType.invoke(watchProviderCountry)
        }
    }

    fun showHomepage() {
        viewModelScope.launch {
            tvShowDetails.value?.homepage?.let {
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
            tvShowDetails.value?.let {
                _uiEffect.emit(TvShowDetailsEffect.NavigateToTvShowDetailsAbout(it))
            }
        }
    }
}

sealed class TvShowDetailsEffect {
    class NavigateToTvShowDetailsAbout(val tvShowDetails: TVShowDetails) : TvShowDetailsEffect()
    class NavigateToWatchNowLink(val link: String) : TvShowDetailsEffect()
    class NavigateToTvShowDetails(val media: Media) : TvShowDetailsEffect()
}