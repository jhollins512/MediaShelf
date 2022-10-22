package com.mediashelf.android.feature.movieDetails.domain

import com.mediashelf.android.core.domain.model.MovieDetails
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetMovieDetailsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return mediaRepository.getMovieDetails(movieId)
    }
}