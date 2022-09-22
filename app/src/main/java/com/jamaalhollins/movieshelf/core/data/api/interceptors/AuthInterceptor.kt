package com.jamaalhollins.movieshelf.core.data.api.interceptors

import com.jamaalhollins.movieshelf.BuildConfig
import com.jamaalhollins.movieshelf.core.data.api.ApiParameters
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val url =
            oldRequest.url.newBuilder()
                .addQueryParameter(ApiParameters.API_KEY, BuildConfig.API_KEY)
                .build()

        val newRequest = oldRequest.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}