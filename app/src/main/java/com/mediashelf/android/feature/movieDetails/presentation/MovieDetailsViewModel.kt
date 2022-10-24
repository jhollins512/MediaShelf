package com.mediashelf.android.feature.movieDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediashelf.android.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.model.MovieDetails
import com.mediashelf.android.core.domain.model.WatchProviderWithViewingOptions
import com.mediashelf.android.core.extensions.createExceptionHandler
import com.mediashelf.android.feature.movieDetails.domain.GetMovieDetailsUseCase
import com.mediashelf.android.feature.movieDetails.domain.GetMovieRecommendationsUseCase
import com.mediashelf.android.feature.movieDetails.domain.GetMovieWatchProvidersForLocaleUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieRecommendations: GetMovieRecommendationsUseCase,
    private val getMovieWatchProvidersForLocale: GetMovieWatchProvidersForLocaleUseCase,
    private val formatWatchProvidersWithType: FormatToWatchProviderWithViewingOptionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState: StateFlow<MovieDetailsUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<MovieDetailsEffect>()
    val uiEffect: SharedFlow<MovieDetailsEffect> = _uiEffect

    fun loadMovieDetailsScreen(movieId: Int) {
        _uiState.update { it.copy(movieDetailsLoading = true, movieDetailsLoadFailure = false) }

        loadMovieDetails(movieId)
        loadMovieWatchProviders(movieId, Locale.getDefault())
        loadSimilarMovies(movieId)
    }

    private fun loadMovieDetails(movieId: Int) {
        val exceptionHandler = viewModelScope.createExceptionHandler("Failed to movie details.") {
            _uiState.update {
                it.copy(
                    movieDetailsLoading = false,
                    movieDetailsLoadFailure = true
                )
            }
        }

        viewModelScope.launch(exceptionHandler) {
            val movieDetails = getMovieDetails.invoke(movieId)

            _uiState.update {
                it.copy(
                    movieDetailsLoading = false,
                    movieDetailsLoadFailure = false,
                    movieDetails = movieDetails
                )
            }
        }
    }

    private fun loadSimilarMovies(movieId: Int) {
        val exceptionHandler =
            viewModelScope.createExceptionHandler("Failed to load similar movies.")

        viewModelScope.launch(exceptionHandler) {
            val similarMovies = getMovieRecommendations.invoke(movieId)

            _uiState.update { it.copy(similarMovies = similarMovies) }
        }
    }

    private fun loadMovieWatchProviders(movieId: Int, locale: Locale) {
        val exceptionHandler =
            viewModelScope.createExceptionHandler("Failed to load movie watch providers.")

        viewModelScope.launch(exceptionHandler) {
            val watchProviderCountry = getMovieWatchProvidersForLocale.invoke(movieId, locale)
            val movieWatchProviders = formatWatchProvidersWithType.invoke(watchProviderCountry)

            _uiState.update {
                it.copy(movieWatchProviders = movieWatchProviders)
            }
        }
    }

    fun showHomepage() {
        viewModelScope.launch {
            _uiState.value.movieDetails?.homepage?.let {
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
            _uiState.value.movieDetails?.let {
                _uiEffect.emit(MovieDetailsEffect.NavigateToMovieDetailsAbout(it))
            }
        }
    }

    fun navigateToWatchProviderAttribution() {
        viewModelScope.launch {
            _uiState.value.movieDetails?.let {
                _uiEffect.emit(MovieDetailsEffect.NavigateWatchProviderAttribution(it))
            }
        }
    }
}

data class MovieDetailsUiState(
    val movieDetailsLoading: Boolean = true,
    val movieDetails: MovieDetails? = null,
    val similarMovies: List<Media> = emptyList(),
    val movieWatchProviders: List<WatchProviderWithViewingOptions> = emptyList(),
    val movieDetailsLoadFailure: Boolean = false
)

sealed class MovieDetailsEffect {
    class NavigateToMovieDetailsAbout(val movieDetails: MovieDetails) : MovieDetailsEffect()
    class NavigateToWatchNowLink(val link: String) : MovieDetailsEffect()
    class NavigateToMovieDetails(val media: Media) : MovieDetailsEffect()
    class NavigateWatchProviderAttribution(val movieDetails: MovieDetails) : MovieDetailsEffect()
}