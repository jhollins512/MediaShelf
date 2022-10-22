package com.mediashelf.android.feature.tvShowDetails.domain

import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetTVShowRecommendationsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvShowId: Int): List<Media> {
        return mediaRepository.getTvShowRecommendations(tvShowId).results
    }
}