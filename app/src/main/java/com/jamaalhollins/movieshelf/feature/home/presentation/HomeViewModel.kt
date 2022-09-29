package com.jamaalhollins.movieshelf.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.feature.home.domain.usecases.*

class HomeViewModel constructor(
    private val getDailyTrendingMoviesUseCase: GetDailyTrendingMoviesUseCase,
    private val getDailyTrendingTVShowsUseCaseUseCase: GetDailyTrendingTVShowsUseCaseUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularTVUseCase: GetPopularTVUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) :
    ViewModel() {

    val trendingMovies: LiveData<List<Media>> = liveData { emit(getDailyTrendingMoviesUseCase()) }

    val trendingTVShows: LiveData<List<Media>> =
        liveData { emit(getDailyTrendingTVShowsUseCaseUseCase()) }

    val popularMovies: LiveData<List<Media>> = liveData { emit(getPopularMoviesUseCase()) }

    val popularTV: LiveData<List<Media>> =
        liveData { emit(getPopularTVUseCase()) }

    val nowPlayingMovies: LiveData<List<Media>> =
        liveData { emit(getNowPlayingMoviesUseCase()) }

    val upcomingMovies: LiveData<List<Media>> =
        liveData { emit(getUpcomingMoviesUseCase()) }
}