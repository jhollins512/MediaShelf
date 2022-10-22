package com.mediashelf.android.core.domain.model

data class PaginatedMedia(
    val page: Int,
    val results: List<Media>,
    val total_pages: Int,
    val total_results: Int
)
