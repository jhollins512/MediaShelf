package com.jamaalhollins.movieshelf.feature.tvShowDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.Credits

class GetTVShowCreditsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvId: Int): Credits {
        return mediaRepository.getTvShowCredits(tvId)
    }
}