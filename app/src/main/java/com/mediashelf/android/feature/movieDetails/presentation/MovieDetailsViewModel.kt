package com.mediashelf.android.feature.movieDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediashelf.android.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.model.MovieDetails
import com.mediashelf.android.core.domain.model.WatchProviderWithViewingOptions
import com.mediashelf.android.feature.movieDetails.domain.GetMovieDetailsUseCase
import com.mediashelf.android.feature.movieDetails.domain.GetMovieRecommendationsUseCase
import com.mediashelf.android.feature.movieDetails.domain.GetMovieWatchProvidersForLocaleUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.*

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieRecommendations: GetMovieRecommendationsUseCase,
    private val getMovieWatchProvidersForLocale: GetMovieWatchProvidersForLocaleUseCase,
    private val formatWatchProvidersWithType: FormatToWatchProviderWithViewingOptionsUseCase
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _similarMovies = MutableLiveData<List<Media>>()
    val similarMovies: LiveData<List<Media>> = _similarMovies

    private val _movieWatchProviders =
        MutableLiveData<List<WatchProviderWithViewingOptions>>()
    val movieWatchProviders: LiveData<List<WatchProviderWithViewingOptions>> =
        _movieWatchProviders

    private val _uiEffect = MutableSharedFlow<MovieDetailsEffect>()
    val uiEffect: SharedFlow<MovieDetailsEffect> = _uiEffect

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = getMovieDetails.invoke(movieId)
        }
    }

    fun loadSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            _similarMovies.value = getMovieRecommendations.invoke(movieId)
        }
    }

    fun loadMovieWatchProviders(movieId: Int, locale: Locale) {
        viewModelScope.launch {
            val watchProviderCountry = getMovieWatchProvidersForLocale.invoke(movieId, locale)
            _movieWatchProviders.value =
                formatWatchProvidersWithType.invoke(watchProviderCountry)
        }
    }

    fun showHomepage() {
        viewModelScope.launch {
            movieDetails.value?.homepage?.let {
                _uiEffect.emit(MovieDetailsEffect.NavigateToWatchNowLink(it))
            }
        }
    }

    fun navigateToMediaDetails(media: Media) {
        viewModelScope.launch {
            _uiEffect.emit(MovieDetailsEffect.NavigateToMovieDetails(media))
        }
    }

    fun navigateToMovieDetailsAbout() {
        viewModelScope.launch {
            movieDetails.value?.let {
                _uiEffect.emit(MovieDetailsEffect.NavigateToMovieDetailsAbout(it))
            }
        }
    }

    fun navigateToWatchProviderAttribution() {
        viewModelScope.launch {
            movieDetails.value?.let {
                _uiEffect.emit(MovieDetailsEffect.NavigateWatchProviderAttribution(it))
            }
        }
    }
}

sealed class MovieDetailsEffect {
    class NavigateToMovieDetailsAbout(val movieDetails: MovieDetails) : MovieDetailsEffect()
    class NavigateToWatchNowLink(val link: String) : MovieDetailsEffect()
    class NavigateToMovieDetails(val media: Media) : MovieDetailsEffect()
    class NavigateWatchProviderAttribution(val movieDetails: MovieDetails) : MovieDetailsEffect()
}