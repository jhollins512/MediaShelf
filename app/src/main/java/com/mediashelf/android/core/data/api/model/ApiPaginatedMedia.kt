package com.mediashelf.android.core.data.api.model

import com.mediashelf.android.core.domain.model.PaginatedMedia
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPaginatedMedia<out T : ApiMedia>(
    val page: Int,
    val results: List<T>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
) {
    fun mapToDomain(): PaginatedMedia {
        val mappedResults = results.map { it.mapToMedia() }

        return PaginatedMedia(page, mappedResults, totalPages, totalResults)
    }
}
