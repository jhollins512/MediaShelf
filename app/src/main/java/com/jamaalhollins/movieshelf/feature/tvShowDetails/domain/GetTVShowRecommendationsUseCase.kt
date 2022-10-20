package com.jamaalhollins.movieshelf.feature.tvShowDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.TMDBMediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository

class GetTVShowRecommendationsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvShowId: Int): List<Media> {
        return mediaRepository.getTvShowRecommendations(tvShowId).results
    }
}