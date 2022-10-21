package com.jamaalhollins.movieshelf.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.data.repository.pagingSource.SearchAllMediaPagingSource
import com.jamaalhollins.movieshelf.core.domain.model.*
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow

class TMDBMediaRepository(private val tmdbService: TMDBService) : MediaRepository {

    override suspend fun getDailyTrendingMovies(page: Int): PaginatedMedia {
        val response = tmdbService.getTrending("movie", "day", page)
        return response.mapToDomain()
    }

    override suspend fun getDailyTrendingTvShows(page: Int): PaginatedMedia {
        val response = tmdbService.getTrending("tv", "day", page)
        return response.mapToDomain()
    }

    override suspend fun getPopularMovies(page: Int): PaginatedMedia {
        val response = tmdbService.getPopularMovies(page)
        return response.mapToDomain()
    }

    override suspend fun getPopularTV(page: Int): PaginatedMedia {
        val response = tmdbService.getPopularTV(page)
        return response.mapToDomain()
    }

    override suspend fun getNowPlayingMovies(page: Int): PaginatedMedia {
        val response = tmdbService.getNowPlayingMovies(page)
        return response.mapToDomain()
    }

    override suspend fun getUpcomingMovies(page: Int): PaginatedMedia {
        val response = tmdbService.getUpcomingMovies(page)
        return response.mapToDomain()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = tmdbService.getMovieDetails(movieId)
        return response.mapToDomain()
    }

    override suspend fun getTVShowDetails(tvId: Int): TVShowDetails {
        val response = tmdbService.getTVShowDetails(tvId)
        return response.mapToDomain()
    }

    override suspend fun getMovieRecommendations(movieId: Int): PaginatedMedia {
        val response = tmdbService.getMovieRecommendations(movieId)
        return response.mapToDomain()
    }

    override suspend fun getMovieWatchProviders(movieId: Int): WatchProviderResults {
        val response = tmdbService.getMovieWatchProviders(movieId)
        return response.mapToDomain()
    }

    override suspend fun getTvShowRecommendations(tvId: Int): PaginatedMedia {
        val response = tmdbService.getTVShowRecommendations(tvId)
        return response.mapToDomain()
    }

    override suspend fun getTvShowWatchProviders(tvId: Int): WatchProviderResults {
        val response = tmdbService.getTVShowWatchProviders(tvId)
        return response.mapToDomain()
    }

    override suspend fun getMovieCredits(movieId: Int): Credits {
        val response = tmdbService.getMovieCredits(movieId)
        return response.mapToDomain()
    }

    override suspend fun getTvShowCredits(tvId: Int): Credits {
        val response = tmdbService.getTVShowCredits(tvId)
        return response.mapToDomain()
    }

    override suspend fun getMovieReleaseDates(movieId: Int): MovieReleaseDates {
        val response = tmdbService.getMovieReleaseDates(movieId)
        return response.mapToDomain()
    }

    override suspend fun getTVShowContentRatings(tvId: Int): TVShowContentRatings {
        val response = tmdbService.getTVShowContentRatings(tvId)
        return response.mapToDomain()
    }

    override fun searchAllMedia(query: String): Flow<PagingData<Media>> {
        return Pager(PagingConfig(20)) {
            SearchAllMediaPagingSource(tmdbService, query)
        }.flow
    }
}