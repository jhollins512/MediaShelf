package com.jamaalhollins.movieshelf.feature.mediaDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails
import com.jamaalhollins.movieshelf.core.domain.model.WatchProvider
import com.jamaalhollins.movieshelf.core.presentation.model.WatchProviderType
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.FormatWatchProvidersWithType
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetMovieDetailsUseCase
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetMovieRecommendationsUseCase
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetMovieWatchProvidersForLocaleUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.*

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieRecommendations: GetMovieRecommendationsUseCase,
    private val getMovieWatchProviders: GetMovieWatchProvidersForLocaleUseCase,
    private val formatWatchProvidersWithType: FormatWatchProvidersWithType
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _movieRecommendations = MutableLiveData<List<Media>>()
    val movieRecommendations: LiveData<List<Media>> = _movieRecommendations

    private val _movieWatchProviders =
        MutableLiveData<List<Pair<WatchProvider, List<WatchProviderType>>>>()
    val movieWatchProviders: LiveData<List<Pair<WatchProvider, List<WatchProviderType>>>> =
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
            _movieRecommendations.value = getMovieRecommendations.invoke(movieId)
        }
    }

    fun loadMovieWatchProviders(movieId: Int, locale: Locale) {
        viewModelScope.launch {
            val watchProviderCountry = getMovieWatchProviders.invoke(movieId, locale)

            watchProviderCountry?.let {
                _movieWatchProviders.value = formatWatchProvidersWithType.invoke(it)
            }
        }
    }

    fun watchNow(link: String?) {
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
}

sealed class MovieDetailsEffect {
    object NavigateToMovieDetailsAbout : MovieDetailsEffect()
    class NavigateToWatchNowLink(val link: String) : MovieDetailsEffect()
    class NavigateToMovieDetails(val media: Media) : MovieDetailsEffect()
}