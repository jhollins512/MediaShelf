package com.mediashelf.android.feature.tvShowDetails.domain

import com.mediashelf.android.core.domain.repository.MediaRepository
import java.util.*

data class GetTVShowContentRatingUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(tvId: Int, locale: Locale): String {
        val tvShowContentRatings =
            mediaRepository.getTVShowContentRatings(tvId).results

        return tvShowContentRatings.find { it.iso31661 == locale.country }?.rating.orEmpty()
    }
}