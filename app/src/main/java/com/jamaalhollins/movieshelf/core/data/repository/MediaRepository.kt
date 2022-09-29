package com.jamaalhollins.movieshelf.core.data.repository

import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.data.api.model.ApiMedia
import com.jamaalhollins.movieshelf.core.data.api.model.ApiMovie
import com.jamaalhollins.movieshelf.core.data.api.model.ApiPaginatedMedia
import com.jamaalhollins.movieshelf.core.data.api.model.ApiTVShow

class MediaRepository(private val tmdbService: TMDBService) {

    suspend fun getDailyTrendingMovies(page: Int = 1): ApiPaginatedMedia<ApiMedia> {
        return tmdbService.getTrending("movie", "day", page)
    }

    suspend fun getDailyTrendingTvShows(page: Int = 1): ApiPaginatedMedia<ApiMedia> {
        return tmdbService.getTrending("tv", "day", page)
    }

    suspend fun getPopularMovies(page: Int = 1): ApiPaginatedMedia<ApiMovie> {
        return tmdbService.getPopularMovies(page)
    }

    suspend fun getPopularTV(page: Int = 1): ApiPaginatedMedia<ApiTVShow> {
        return tmdbService.getPopularTV(page)
    }

    suspend fun getNowPlayingMovies(page: Int = 1): ApiPaginatedMedia<ApiMovie> {
        return tmdbService.getNowPlayingMovies(page)
    }

    suspend fun getUpcomingMovies(page: Int = 1): ApiPaginatedMedia<ApiMovie> {
        return tmdbService.getUpcomingMovies(page)
    }
}