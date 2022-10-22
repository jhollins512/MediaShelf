package com.mediashelf.android.feature.home.domain.usecases

import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.repository.MediaRepository

class GetDailyTrendingTVShowsUseCaseUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(): List<Media> {
        return  mediaRepository.getDailyTrendingTvShows().results
    }
}