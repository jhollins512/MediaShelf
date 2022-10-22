package com.mediashelf.android.core.extensions

import android.content.Context
import android.content.Intent
import android.webkit.URLUtil
import androidx.core.net.toUri

fun String.navigateToExternalUrl(context: Context) {
    if (URLUtil.isValidUrl(this)) {
        context.startActivity(Intent(Intent.ACTION_VIEW, toUri()))
    }
}