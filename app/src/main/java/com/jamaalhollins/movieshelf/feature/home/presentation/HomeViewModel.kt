package com.jamaalhollins.movieshelf.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.feature.home.domain.usecases.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel constructor(
    private val getDailyTrendingMovies: GetDailyTrendingMoviesUseCase,
    private val getDailyTrendingTVShows: GetDailyTrendingTVShowsUseCaseUseCase,
    private val getPopularMovies: GetPopularMoviesUseCase,
    private val getPopularTV: GetPopularTVUseCase,
    private val getNowPlayingMovies: GetNowPlayingMoviesUseCase,
    private val getUpcomingMovies: GetUpcomingMoviesUseCase
) :
    ViewModel() {

    private val _uiState = MutableLiveData(HomeUiState())
    val uiState: LiveData<HomeUiState> = _uiState

    init {
        loadMedia()
    }

    private fun loadMedia() {
        viewModelScope.launch {
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

    fun navigateToMediaDetails(media: Media) {
        Timber.d(media.title)
    }
}

class HomeUiState(
    val loading: Boolean = true,
    val trendingMovies: List<Media> = emptyList(),
    val trendingTVShows: List<Media> = emptyList(),
    val popularMovies: List<Media> = emptyList(),
    val popularTVShows: List<Media> = emptyList(),
    val nowPlayingMovies: List<Media> = emptyList(),
    val upcomingMovies: List<Media> = emptyList()
)