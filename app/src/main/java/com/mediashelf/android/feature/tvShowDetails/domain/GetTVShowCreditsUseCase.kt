package com.mediashelf.android.feature.tvShowDetails.domain

import com.mediashelf.android.core.domain.model.Credits
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetTVShowCreditsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvId: Int): Credits {
        return mediaRepository.getTvShowCredits(tvId)
    }
}