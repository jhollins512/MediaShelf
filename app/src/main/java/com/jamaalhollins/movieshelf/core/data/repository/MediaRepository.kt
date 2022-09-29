package com.jamaalhollins.movieshelf.core.data.repository

import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.domain.model.PaginatedMedia

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
}