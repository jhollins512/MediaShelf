package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.core.data.api.ApiConstants
import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.data.api.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideTMDBService() }
}

private fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun provideAuthInterceptor() = AuthInterceptor()

private fun provideOkHttpClient() =
    OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(provideAuthInterceptor())
        .build()

private fun provideTMDBService(): TMDBService {
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_ENDPOINT)
        .client(provideOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TMDBService::class.java)
}