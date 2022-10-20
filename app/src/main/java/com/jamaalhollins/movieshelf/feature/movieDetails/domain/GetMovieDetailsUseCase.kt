package com.jamaalhollins.movieshelf.feature.movieDetails.domain

import com.jamaalhollins.movieshelf.core.domain.model.MovieDetails
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository

class GetMovieDetailsUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return mediaRepository.getMovieDetails(movieId)
    }
}