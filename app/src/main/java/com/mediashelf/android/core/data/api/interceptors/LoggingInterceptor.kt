package com.mediashelf.android.core.data.api.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class LoggingInterceptor : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.i(message)
    }
}