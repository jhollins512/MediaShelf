package com.mediashelf.android.feature.movieDetails.domain

import com.mediashelf.android.core.domain.model.Credits
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetMovieCreditsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): Credits {
        return mediaRepository.getMovieCredits(movieId)
    }
}