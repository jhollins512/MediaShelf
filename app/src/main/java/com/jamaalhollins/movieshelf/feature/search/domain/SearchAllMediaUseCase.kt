package com.jamaalhollins.movieshelf.feature.search.domain

import androidx.paging.PagingData
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow

class SearchAllMediaUseCase(private val mediaRepository: MediaRepository) {
    operator fun invoke(query: String): Flow<PagingData<Media>> {
        return mediaRepository.searchAllMedia(query)
    }
}