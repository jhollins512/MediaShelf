package com.mediashelf.android.core.domain.repository

import androidx.paging.PagingData
import com.mediashelf.android.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun getDailyTrendingMovies(page: Int = 1): PaginatedMedia

    suspend fun getDailyTrendingTvShows(page: Int = 1): PaginatedMedia

    suspend fun getPopularMovies(page: Int = 1): PaginatedMedia

    suspend fun getPopularTV(page: Int = 1): PaginatedMedia

    suspend fun getNowPlayingMovies(page: Int = 1): PaginatedMedia

    suspend fun getUpcomingMovies(page: Int = 1): PaginatedMedia

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getTVShowDetails(tvId: Int): TVShowDetails

    suspend fun getMovieRecommendations(movieId: Int): PaginatedMedia

    suspend fun getMovieWatchProviders(movieId: Int): WatchProviderResults

    suspend fun getTvShowRecommendations(tvId: Int): PaginatedMedia

    suspend fun getTvShowWatchProviders(tvId: Int): WatchProviderResults

    suspend fun getMovieCredits(movieId: Int): Credits

    suspend fun getTvShowCredits(tvId: Int): Credits

    suspend fun getMovieReleaseDates(movieId: Int): MovieReleaseDates

    suspend fun getTVShowContentRatings(tvId: Int): TVShowContentRatings

    fun searchAllMedia(query: String): Flow<PagingData<Media>>
}