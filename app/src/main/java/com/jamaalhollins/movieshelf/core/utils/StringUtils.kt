package com.jamaalhollins.movieshelf.core.utils

import android.content.Context
import com.jamaalhollins.movieshelf.R
import com.jamaalhollins.movieshelf.core.presentation.model.WatchProviderViewingOption

fun formatWatchProviderOptionsText(
    context: Context,
    watchProviderTypes: List<WatchProviderViewingOption>
): String {
    return watchProviderTypes.joinToString(" / ") {
        when (it) {
            WatchProviderViewingOption.STREAMING -> {
                context.getString(R.string.label_stream)
            }
            WatchProviderViewingOption.RENT -> {
                context.getString(R.string.label_rent)
            }
            WatchProviderViewingOption.BUY -> {
                context.getString(R.string.label_buy)
            }
        }
    }
}