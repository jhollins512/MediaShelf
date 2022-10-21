package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.jamaalhollins.movieshelf.feature.home.domain.usecases.*
import com.jamaalhollins.movieshelf.feature.movieDetails.domain.*
import com.jamaalhollins.movieshelf.feature.search.domain.SearchAllMediaUseCase
import com.jamaalhollins.movieshelf.feature.tvShowDetails.domain.*
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
    factory { GetTVShowRecommendationsUseCase(get()) }
    factory { GetTVShowWatchProvidersForLocaleUseCase(get()) }
    factory { FormatToWatchProviderWithViewingOptionsUseCase() }
    factory { GetMovieCreditsUseCase(get()) }
    factory { GetMovieContentRatingUseCase(get()) }
    factory { GetTVShowCreditsUseCase(get()) }
    factory { GetTVShowContentRatingUseCase(get()) }
    factory { SearchAllMediaUseCase(get()) }
}