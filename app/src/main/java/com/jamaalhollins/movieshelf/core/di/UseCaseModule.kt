package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.feature.home.domain.usecases.*
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetMovieDetailsUseCase
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.GetTVShowDetailsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetDailyTrendingMoviesUseCase(get()) }
    factory { GetDailyTrendingTVShowsUseCaseUseCase(get()) }
    factory { GetPopularMoviesUseCase(get()) }
    factory { GetPopularTVUseCase(get()) }
    factory { GetNowPlayingMoviesUseCase(get()) }
    factory { GetUpcomingMoviesUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
    factory { GetTVShowDetailsUseCase(get()) }
}