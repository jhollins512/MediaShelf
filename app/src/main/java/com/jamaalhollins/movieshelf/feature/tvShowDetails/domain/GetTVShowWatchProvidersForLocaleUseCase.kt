package com.jamaalhollins.movieshelf.feature.tvShowDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderCountry
import java.util.*

class GetTVShowWatchProvidersForLocaleUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(
        tvId: Int,
        locale: Locale
    ): WatchProviderCountry? {
        return mediaRepository.getTvShowWatchProviders(tvId).results[locale.country]
    }
}