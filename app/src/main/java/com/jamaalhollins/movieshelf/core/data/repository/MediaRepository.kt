package com.jamaalhollins.movieshelf.core.data.repository

import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails
import com.jamaalhollins.movieshelf.core.domain.model.PaginatedMedia
import com.jamaalhollins.movieshelf.core.domain.model.TVShowDetails
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderResults

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
        val response = tmdbService.getTvShowRecommendations(tvId)
        return response.mapToDomain()
    }

    suspend fun getTvShowWatchProviders(tvId: Int): WatchProviderResults {
        val response = tmdbService.getTvShowWatchProviders(tvId)
        return response.mapToDomain()
    }
}