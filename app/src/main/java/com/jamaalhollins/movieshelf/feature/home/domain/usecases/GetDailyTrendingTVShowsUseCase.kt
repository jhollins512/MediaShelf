package com.jamaalhollins.movieshelf.feature.home.domain.usecases

import com.jamaalhollins.movieshelf.core.data.repository.MediaRepository
import com.jamaalhollins.movieshelf.core.domain.model.Media

class GetDailyTrendingTVShowsUseCaseUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(): List<Media> {
        return  mediaRepository.getDailyTrendingTvShows().results
    }
}