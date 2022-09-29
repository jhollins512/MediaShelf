package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovie(
    val title: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "release_date") val releaseDate: String,
    val video: Boolean
) : ApiMedia() {

    override fun mapToMedia(): Media {
        return Media(id, title, mediaType.orEmpty(), posterPath.orEmpty())
    }
}
