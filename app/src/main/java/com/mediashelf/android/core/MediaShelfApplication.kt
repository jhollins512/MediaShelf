package com.mediashelf.android.core

import android.app.Application
import com.mediashelf.android.core.di.KoinHelper
import com.mediashelf.android.core.logging.Logger

class MediaShelfApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.init()
        KoinHelper.initKoin(this)
    }
}