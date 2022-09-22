package com.jamaalhollins.movieshelf.core.logging

import com.jamaalhollins.movieshelf.BuildConfig
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


object Logger {
    fun init() {
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}