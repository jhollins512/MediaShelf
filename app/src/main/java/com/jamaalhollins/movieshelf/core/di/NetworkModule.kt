package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.BuildConfig
import com.jamaalhollins.movieshelf.core.data.api.ApiConstants
import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.data.api.interceptors.AuthInterceptor
import com.jamaalhollins.movieshelf.core.data.api.model.ApiMedia
import com.jamaalhollins.movieshelf.core.data.api.model.ApiMovie
import com.jamaalhollins.movieshelf.core.data.api.model.ApiPerson
import com.jamaalhollins.movieshelf.core.data.api.model.ApiTVShow
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideTMDBService() }
}

private fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

private fun provideAuthInterceptor() = AuthInterceptor()

private fun provideOkHttpClient() =
    OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(provideAuthInterceptor())
        .build()

private fun provideMoshi(): Moshi {
    val factory = PolymorphicJsonAdapterFactory.of(ApiMedia::class.java, "media_type")
        .withSubtype(ApiMovie::class.java, "movie")
        .withSubtype(ApiTVShow::class.java, "tv")
        .withSubtype(ApiPerson::class.java, "person")

    return Moshi.Builder().add(factory).add(KotlinJsonAdapterFactory())
        .build()
}

private fun provideTMDBService(): TMDBService {
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_ENDPOINT)
        .client(provideOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .build()
        .create(TMDBService::class.java)
}