package com.jamaalhollins.movieshelf.feature.tvShowDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.Media

class GetTvShowRecommendationsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvShowId: Int): List<Media> {
        return mediaRepository.getTvShowRecommendations(tvShowId).results
    }
}