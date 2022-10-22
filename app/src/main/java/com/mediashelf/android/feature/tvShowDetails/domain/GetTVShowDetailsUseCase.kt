package com.mediashelf.android.feature.tvShowDetails.domain

import com.mediashelf.android.core.domain.model.TVShowDetails
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetTVShowDetailsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvId: Int): TVShowDetails {
        return mediaRepository.getTVShowDetails(tvId)
    }
}