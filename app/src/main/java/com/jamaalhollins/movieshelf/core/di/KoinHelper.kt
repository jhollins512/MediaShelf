package com.jamaalhollins.movieshelf.core.di

import com.jamaalhollins.movieshelf.core.MovieShelfApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object KoinHelper {
    fun initKoin(application: MovieShelfApplication) {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }
    }
}