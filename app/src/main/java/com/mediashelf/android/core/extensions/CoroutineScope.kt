package com.mediashelf.android.core.extensions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

fun CoroutineScope.createExceptionHandler(
    message: String,
    action: ((throwable: Throwable) -> Unit)? = null
) = CoroutineExceptionHandler { _, throwable ->
    Timber.e(throwable, message)
    launch {
        action?.invoke(throwable)
    }
}