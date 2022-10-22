package com.mediashelf.android.feature.movieDetails.domain

import com.mediashelf.android.core.domain.model.WatchProviderCountry
import com.mediashelf.android.core.domain.repository.MediaRepository
import java.util.*

class GetMovieWatchProvidersForLocaleUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(
        movieId: Int,
        locale: Locale
    ): WatchProviderCountry? {
        return mediaRepository.getMovieWatchProviders(movieId).results[locale.country]
    }
}