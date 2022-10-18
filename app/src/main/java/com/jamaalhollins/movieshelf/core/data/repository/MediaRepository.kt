package com.jamaalhollins.movieshelf.core.data.repository

import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.domain.model.*

class MediaRepository(private val tmdbService: TMDBService) {

    suspend fun getDailyTrendingMovies(page: Int = 1): PaginatedMedia {
        val response = tmdbService.getTrending("movie", "day", page)
        return response.mapToDomain()
    }

    suspend fun getDailyTrendingTvShows(page: Int = 1): PaginatedMedia {
        val response = tmdbService.getTrending("tv", "day", page)
        return response.mapToDomain()
    }

    suspend fun getPopularMovies(page: Int = 1): PaginatedMedia {
        val response = tmdbService.getPopularMovies(page)
        return response.mapToDomain()
    }

    suspend fun getPopularTV(page: Int = 1): PaginatedMedia {
        val response = tmdbService.getPopularTV(page)
        return response.mapToDomain()
    }

    suspend fun getNowPlayingMovies(page: Int = 1): PaginatedMedia {
        val response = tmdbService.getNowPlayingMovies(page)
        return response.mapToDomain()
    }

    suspend fun getUpcomingMovies(page: Int = 1): PaginatedMedia {
        val response = tmdbService.getUpcomingMovies(page)
        return response.mapToDomain()
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = tmdbService.getMovieDetails(movieId)
        return response.mapToDomain()
    }

    suspend fun getTVShowDetails(tvId: Int): TVShowDetails {
        val response = tmdbService.getTVShowDetails(tvId)
        return response.mapToDomain()
    }

    suspend fun getMovieRecommendations(movieId: Int): PaginatedMedia {
        val response = tmdbService.getMovieRecommendations(movieId)
        return response.mapToDomain()
    }

    suspend fun getMovieWatchProviders(movieId: Int): WatchProviderResults {
        val response = tmdbService.getMovieWatchProviders(movieId)
        return response.mapToDomain()
    }

    suspend fun getTvShowRecommendations(tvId: Int): PaginatedMedia {
        val response = tmdbService.getTVShowRecommendations(tvId)
        return response.mapToDomain()
    }

    suspend fun getTvShowWatchProviders(tvId: Int): WatchProviderResults {
        val response = tmdbService.getTVShowWatchProviders(tvId)
        return response.mapToDomain()
    }

    suspend fun getMovieCredits(movieId: Int): Credits {
        val response = tmdbService.getMovieCredits(movieId)
        return response.mapToDomain()
    }

    suspend fun getTvShowCredits(tvId: Int): Credits {
        val response = tmdbService.getTVShowCredits(tvId)
        return response.mapToDomain()
    }

    suspend fun getMovieReleaseDates(movieId: Int): MovieReleaseDates {
        val response = tmdbService.getMovieReleaseDates(movieId)
        return response.mapToDomain()
    }

    suspend fun getTVShowContentRatings(tvId: Int): TVShowContentRatings {
        val response = tmdbService.getTVShowContentRatings(tvId)
        return response.mapToDomain()
    }
}