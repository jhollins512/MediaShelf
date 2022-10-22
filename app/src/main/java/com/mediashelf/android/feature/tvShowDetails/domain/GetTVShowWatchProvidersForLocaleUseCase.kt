package com.mediashelf.android.feature.tvShowDetails.domain

import com.mediashelf.android.core.domain.model.WatchProviderCountry
import com.mediashelf.android.core.domain.repository.MediaRepository
import java.util.*

class GetTVShowWatchProvidersForLocaleUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(
        tvId: Int,
        locale: Locale
    ): WatchProviderCountry? {
        return mediaRepository.getTvShowWatchProviders(tvId).results[locale.country]
    }
}