package com.jamaalhollins.movieshelf.feature.mediaDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.TVShowDetails

class GetTVShowDetailsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvId: Int): TVShowDetails {
        return mediaRepository.getTVShowDetails(tvId)
    }
}