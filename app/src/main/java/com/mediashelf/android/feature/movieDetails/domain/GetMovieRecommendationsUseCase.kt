package com.mediashelf.android.feature.movieDetails.domain

import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetMovieRecommendationsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): List<Media> {
        return mediaRepository.getMovieRecommendations(movieId).results
    }
}