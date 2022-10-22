package com.mediashelf.android.feature.home.domain.usecases

import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetNowPlayingMoviesUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(): List<Media> {
        return mediaRepository.getNowPlayingMovies().results
    }
}