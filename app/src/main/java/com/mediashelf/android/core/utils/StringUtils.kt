package com.mediashelf.android.core.utils

import android.content.Context
import com.mediashelf.android.R
import com.mediashelf.android.core.presentation.model.WatchProviderViewingOption

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