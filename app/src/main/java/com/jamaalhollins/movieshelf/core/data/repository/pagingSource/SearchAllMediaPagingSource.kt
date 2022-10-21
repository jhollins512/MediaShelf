package com.jamaalhollins.movieshelf.core.data.repository.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jamaalhollins.movieshelf.core.data.api.TMDBService
import com.jamaalhollins.movieshelf.core.domain.model.Media
import timber.log.Timber

class SearchAllMediaPagingSource(private val tmdbService: TMDBService, private val query: String) :
    PagingSource<Int, Media>() {

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = tmdbService.searchAllMedia(query, nextPageNumber)

            //We don't want person to be included in the results for now.
            val media = response.results
                .map { it.mapToMedia() }

            Timber.d((nextPageNumber + 1).toString())
            return LoadResult.Page(
                data = media,
                prevKey = null,
                nextKey = if (nextPageNumber + 1 <= response.totalPages) nextPageNumber + 1 else null
            )
        } catch (exception: Exception) {
            Timber.e(exception)
            return LoadResult.Error(exception)
        }
    }
}