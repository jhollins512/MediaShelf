package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.jamaalhollins.movieshelf.feature.home.domain.usecases.*
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieDetailsUseCase
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieRecommendationsUseCase
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.GetMovieWatchProvidersForLocaleUseCase
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.GetTVShowDetailsUseCase
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.GetTVShowWatchProvidersForLocaleUseCase
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.GetTvShowRecommendationsUseCase
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
    factory { GetTvShowRecommendationsUseCase(get()) }
    factory { GetTVShowWatchProvidersForLocaleUseCase(get()) }
    factory { FormatToWatchProviderWithViewingOptionsUseCase() }
}