package com.jamaalhollins.movieshelf.core.domain.model

import com.jamaalhollins.movieshelf.core.data.api.ApiConstants

data class Media(
    val id: Int,
    val title: String,
    val mediaType: String,
    private val posterPath: String
) {
    fun getPosterPath(): String {
        return ApiConstants.IMAGE_BASE_URL_W500 + posterPath
    }
}
