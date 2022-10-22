package com.mediashelf.android.core.logging

import com.mediashelf.android.BuildConfig
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


object Logger {
    fun init() {
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}