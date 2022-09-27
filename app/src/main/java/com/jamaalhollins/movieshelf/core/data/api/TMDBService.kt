package com.jamaalhollins.movieshelf.core.data.api

import com.jamaalhollins.movieshelf.core.data.api.model.ApiPaginatedMedia
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path(ApiParameters.MEDIA_TYPE) mediaType: String,
        @Path(ApiParameters.TIME_WINDOW) timeWindow: String,
        @Query(ApiParameters.PAGE) page: Int
    ): ApiPaginatedMedia
}