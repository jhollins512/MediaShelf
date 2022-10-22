package com.mediashelf.android.core.domain

import com.mediashelf.android.core.domain.model.WatchProviderCountry
import com.mediashelf.android.core.domain.model.WatchProviderWithViewingOptions
import com.mediashelf.android.core.presentation.model.WatchProviderViewingOption

class FormatToWatchProviderWithViewingOptionsUseCase {
    operator fun invoke(
        watchProviderCountry: WatchProviderCountry?
    ): List<WatchProviderWithViewingOptions> {

        return if (watchProviderCountry == null) {
            emptyList()
        } else {
            val mergedWatchProvidersSet =
                (watchProviderCountry.buy + watchProviderCountry.rent + watchProviderCountry.flatrate).toSet()

            val watchProvidersWithViewingOptions =
                mutableListOf<WatchProviderWithViewingOptions>()

            for (provider in mergedWatchProvidersSet) {
                val watcherProviderTypes = mutableListOf<WatchProviderViewingOption>()

                if (watchProviderCountry.flatrate.contains(provider)) {
                    watcherProviderTypes.add(WatchProviderViewingOption.STREAMING)
                }
                if (watchProviderCountry.rent.contains(provider)) {
                    watcherProviderTypes.add(WatchProviderViewingOption.RENT)
                }
                if (watchProviderCountry.buy.contains(provider)) {
                    watcherProviderTypes.add(WatchProviderViewingOption.BUY)
                }

                val watchProviderWithViewingOption = WatchProviderWithViewingOptions(
                    provider.logoPath,
                    provider.providerId,
                    provider.providerName,
                    watcherProviderTypes
                )

                watchProvidersWithViewingOptions.add(watchProviderWithViewingOption)
            }

            watchProvidersWithViewingOptions
        }
    }
}