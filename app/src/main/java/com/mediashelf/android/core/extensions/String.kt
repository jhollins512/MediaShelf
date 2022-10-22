package com.mediashelf.android.core.extensions

import android.content.Context
import android.content.Intent
import android.webkit.URLUtil
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

fun String.navigateToExternalUrl(context: Context) {
    if (URLUtil.isValidUrl(this)) {
        context.startActivity(Intent(Intent.ACTION_VIEW, toUri()))
    }
}

fun String.navigateToExternalUrlWithCustomTabs(context: Context) {
    if (URLUtil.isValidUrl(this)) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(
            context,
            this.toUri()
        )
    }
}