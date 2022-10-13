package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.feature.home.domain.usecases.*
import com.jamaalhollins.movieshelf.feature.mediaDetails.domain.*
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
    factory { GetMovieRecommendationsUseCase(get()) }
    factory { GetMovieWatchProvidersForLocaleUseCase(get()) }
    factory { FormatWatchProvidersWithType() }
}