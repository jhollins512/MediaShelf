package com.jamaalhollins.movieshelf.core.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPaginatedMedia<out T : ApiMedia>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)
