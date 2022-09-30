package com.jamaalhollins.movieshelf.core.data.api

import com.jamaalhollins.movieshelf.core.data.api.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path(ApiParameters.MEDIA_TYPE) mediaType: String,
        @Path(ApiParameters.TIME_WINDOW) timeWindow: String,
        @Query(ApiParameters.PAGE) page: Int
    ): ApiPaginatedMedia<ApiMedia>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(ApiParameters.PAGE) page: Int
    ): ApiPaginatedMedia<ApiMovie>

    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query(ApiParameters.PAGE) page: Int
    ): ApiPaginatedMedia<ApiTVShow>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query(ApiParameters.PAGE) page: Int
    ): ApiPaginatedMedia<ApiMovie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query(ApiParameters.PAGE) page: Int
    ): ApiPaginatedMedia<ApiMovie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path(ApiParameters.MOVIE_ID) movieId: Int): ApiMovieDetails

    @GET("tv/{tv_id}")
    suspend fun getTVShowDetails(@Path(ApiParameters.TV_ID) movieId: Int): ApiTVShowDetails
}