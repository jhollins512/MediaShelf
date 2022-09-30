package com.jamaalhollins.movieshelf.feature.mediaDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails

class GetMovieDetailsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return mediaRepository.getMovieDetails(movieId)
    }
}