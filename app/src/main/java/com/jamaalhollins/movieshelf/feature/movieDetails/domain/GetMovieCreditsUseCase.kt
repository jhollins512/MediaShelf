package com.jamaalhollins.movieshelf.feature.movieDetails.domain

import com.jamaalhollins.movieshelf.core.domain.model.Credits
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository

class GetMovieCreditsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): Credits {
        return mediaRepository.getMovieCredits(movieId)
    }
}