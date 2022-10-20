package com.jamaalhollins.movieshelf.core.domain.repository

import com.jamaalhollins.movieshelf.core.domain.model.*

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

    suspend fun searchAllMedia(query: String)
}