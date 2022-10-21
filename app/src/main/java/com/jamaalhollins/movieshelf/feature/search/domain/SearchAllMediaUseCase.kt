package com.jamaalhollins.movieshelf.feature.search.domain

import androidx.paging.PagingData
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.domain.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchAllMediaUseCase(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke(query: String): Flow<PagingData<Media>> {
        return withContext(Dispatchers.IO) { mediaRepository.searchAllMedia(query) }
    }
}