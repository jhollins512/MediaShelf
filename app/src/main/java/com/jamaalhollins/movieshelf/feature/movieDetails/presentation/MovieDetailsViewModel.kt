package com.jamaalhollins.movieshelf.feature.movieDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderWithViewingOptions
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieDetailsUseCase
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieRecommendationsUseCase
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieWatchProvidersForLocaleUseCase
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

    fun showHomepage(link: String?) {
        viewModelScope.launch {
            link?.let {
                _uiEffect.emit(MovieDetailsEffect.NavigateToWatchNowLink(link))
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
}

sealed class MovieDetailsEffect {
    class NavigateToMovieDetailsAbout(val movieDetails: MovieDetails) : MovieDetailsEffect()
    class NavigateToWatchNowLink(val link: String) : MovieDetailsEffect()
    class NavigateToMovieDetails(val media: Media) : MovieDetailsEffect()
}