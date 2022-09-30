package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGenre(
    @Json(name = "id") val id: Int, @Json(name = "name") val name: String
) {
    fun mapToDomain(): Genre {
        return Genre(name)
    }
}
