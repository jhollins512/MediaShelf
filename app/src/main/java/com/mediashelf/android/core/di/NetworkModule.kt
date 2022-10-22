package com.mediashelf.android.core.di

import android.content.Context
import com.mediashelf.android.BuildConfig
import com.mediashelf.android.core.data.api.ApiConstants
import com.mediashelf.android.core.data.api.TMDBService
import com.mediashelf.android.core.data.api.interceptors.AuthInterceptor
import com.mediashelf.android.core.data.api.model.ApiMedia
import com.mediashelf.android.core.data.api.model.ApiMovie
import com.mediashelf.android.core.data.api.model.ApiPerson
import com.mediashelf.android.core.data.api.model.ApiTVShow
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val networkModule = module {
    single { provideTMDBService(androidContext()) }
}

private fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

private fun provideAuthInterceptor() = AuthInterceptor()

private fun provideOkHttpClient(context: Context): OkHttpClient {
    val cacheSize = 10 * 1024 * 1024 // 10 MB

    val cache = Cache(context.cacheDir, cacheSize.toLong())

    return OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(provideAuthInterceptor())
        .cache(cache)
        .build()
}


private fun provideMoshi(): Moshi {
    val factory = PolymorphicJsonAdapterFactory.of(ApiMedia::class.java, "media_type")
        .withSubtype(ApiMovie::class.java, "movie")
        .withSubtype(ApiTVShow::class.java, "tv")
        .withSubtype(ApiPerson::class.java, "person")

    return Moshi.Builder().add(factory).add(KotlinJsonAdapterFactory())
        .build()
}

private fun provideTMDBService(context: Context): TMDBService {
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_ENDPOINT)
        .client(provideOkHttpClient(context))
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .build()
        .create(TMDBService::class.java)
}