package com.mediashelf.android.core.di

import com.mediashelf.android.core.domain.FormatToWatchProviderWithViewingOptionsUseCase
import com.mediashelf.android.feature.home.domain.usecases.*
import com.mediashelf.android.feature.movieDetails.domain.*
import com.mediashelf.android.feature.search.domain.SearchAllMediaUseCase
import com.mediashelf.android.feature.tvShowDetails.domain.*
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