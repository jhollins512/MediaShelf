package com.jamaalhollins.movieshelf.core.utils

import android.content.Context
import android.content.res.Configuration

fun isDarkModeEnabled(context: Context): Boolean {
    return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO -> false
        Configuration.UI_MODE_NIGHT_YES -> true
        else -> false
    }
}