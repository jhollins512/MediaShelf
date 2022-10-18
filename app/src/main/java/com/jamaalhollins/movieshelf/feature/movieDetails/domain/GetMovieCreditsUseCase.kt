package com.jamaalhollins.movieshelf.feature.movieDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.Credits

class GetMovieCreditsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): Credits {
        return mediaRepository.getMovieCredits(movieId)
    }
}