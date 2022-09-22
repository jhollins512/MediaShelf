package com.jamaalhollins.movieshelf.core.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface TMDBService {

    @GET("trending/all/day")
    suspend fun getTrending(): Response<ResponseBody>

}