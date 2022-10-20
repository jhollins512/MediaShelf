package com.jamaalhollins.movieshelf.feature.tvShowDetails.domain

import com.jamaalhollins.movieshelf.core.domain.model.TVShowDetails
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository

class GetTVShowDetailsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvId: Int): TVShowDetails {
        return mediaRepository.getTVShowDetails(tvId)
    }
}