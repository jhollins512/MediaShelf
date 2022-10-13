package com.jamaalhollins.movieshelf.feature.mediaDetails.domain

import com.jamaalhollins.movieshelf.core.domain.model.WatchProvider
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderCountry
import com.jamaalhollins.movieshelf.core.presentation.model.WatchProviderType

class FormatWatchProvidersWithType {
    operator fun invoke(
        watchProviderCountry: WatchProviderCountry
    ): List<Pair<WatchProvider, List<WatchProviderType>>> {

        val mergedWatchProvidersSet =
            (watchProviderCountry.buy + watchProviderCountry.rent + watchProviderCountry.flatrate).toSet()

        val watchProvidersWithType = mutableListOf<Pair<WatchProvider, List<WatchProviderType>>>()

        for (provider in mergedWatchProvidersSet) {
            val watcherProviderTypes = mutableListOf<WatchProviderType>()

            if (watchProviderCountry.flatrate.contains(provider)) {
                watcherProviderTypes.add(WatchProviderType.STREAMING)
            }
            if (watchProviderCountry.rent.contains(provider)) {
                watcherProviderTypes.add(WatchProviderType.RENT)
            }
            if (watchProviderCountry.buy.contains(provider)) {
                watcherProviderTypes.add(WatchProviderType.BUY)
            }

            watchProvidersWithType.add(Pair(provider, watcherProviderTypes))
        }

        return watchProvidersWithType
    }
}