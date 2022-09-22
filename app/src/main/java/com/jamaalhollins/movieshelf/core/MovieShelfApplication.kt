package com.jamaalhollins.movieshelf.core

import android.app.Application
import com.jamaalhollins.movieshelf.core.di.KoinHelper
import com.jamaalhollins.movieshelf.core.logging.Logger

class MovieShelfApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.init()
        KoinHelper.initKoin(this)
    }
}