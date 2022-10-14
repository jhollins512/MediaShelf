package com.jamaalhollins.movieshelf.feature.movieDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderCountry
import java.util.*

class GetMovieWatchProvidersForLocaleUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(
        movieId: Int,
        locale: Locale
    ): WatchProviderCountry? {
        return mediaRepository.getMovieWatchProviders(movieId).results[locale.country]
    }
}