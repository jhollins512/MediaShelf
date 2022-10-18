package com.jamaalhollins.movieshelf.feature.movieDetails.domain

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import java.util.*

class GetMovieContentRatingUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(movieId: Int, locale: Locale): String {
        val releaseDate =
            mediaRepository.getMovieReleaseDates(movieId).results.find { it.iso31661 == locale.country }

        //Type 3 means theatrical and 4 means digital.
        val contentRating =
            releaseDate?.releaseDates?.find { it.type == 3 || it.type == 4 }?.certification.orEmpty()

        return contentRating
    }
}