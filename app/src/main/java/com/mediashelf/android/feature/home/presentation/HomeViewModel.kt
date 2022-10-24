package com.mediashelf.android.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.extensions.createExceptionHandler
import com.mediashelf.android.feature.home.domain.usecases.*
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val getDailyTrendingMovies: GetDailyTrendingMoviesUseCase,
    private val getDailyTrendingTVShows: GetDailyTrendingTVShowsUseCaseUseCase,
    private val getPopularMovies: GetPopularMoviesUseCase,
    private val getPopularTV: GetPopularTVUseCase,
    private val getNowPlayingMovies: GetNowPlayingMoviesUseCase,
    private val getUpcomingMovies: GetUpcomingMoviesUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<HomeUiEffect>()
    val uiEffect: SharedFlow<HomeUiEffect> = _uiEffect

    fun loadMedia() {
        val exceptionHandler = viewModelScope.createExceptionHandler("Failed to load home page") {
            onFailure(it)
        }

        viewModelScope.launch(exceptionHandler) {
            _uiState.update { it.copy(loading = true, loadFailed = false) }

            val trendingMoviesJob = async { getDailyTrendingMovies() }
            val trendingTVShowsJob = async { getDailyTrendingTVShows() }
            val popularMoviesJob = async { getPopularMovies() }
            val popularTVJob = async { getPopularTV() }
            val nowPlayingMoviesJob = async { getNowPlayingMovies() }
            val upcomingMoviesJob = async { getUpcomingMovies() }

            _uiState.value = HomeUiState(
                false,
                trendingMoviesJob.await(),
                trendingTVShowsJob.await(),
                popularMoviesJob.await(),
                popularTVJob.await(),
                nowPlayingMoviesJob.await(),
                upcomingMoviesJob.await()
            )
        }
    }

    private fun onFailure(error: Throwable) {
        _uiState.update { it.copy(loading = false, loadFailed = true) }
    }

    fun navigateToMediaDetails(media: Media) {
        viewModelScope.launch {
            _uiEffect.emit(HomeUiEffect.NavigateToMovieDetails(media))
        }
    }
}

data class HomeUiState(
    val loading: Boolean = true,
    val trendingMovies: List<Media> = emptyList(),
    val trendingTVShows: List<Media> = emptyList(),
    val popularMovies: List<Media> = emptyList(),
    val popularTVShows: List<Media> = emptyList(),
    val nowPlayingMovies: List<Media> = emptyList(),
    val upcomingMovies: List<Media> = emptyList(),
    val loadFailed: Boolean = false
)

sealed class HomeUiEffect {
    class NavigateToMovieDetails(val media: Media) : HomeUiEffect()
}