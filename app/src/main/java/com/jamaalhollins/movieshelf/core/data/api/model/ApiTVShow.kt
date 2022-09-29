package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiTVShow(
    val name: String,
    @Json(name = "original_name") val originalName: String,
    @Json(name = "first_air_date") val firstAirDate: String,
    @Json(name = "origin_country") val originCountry: List<String>
) : ApiMedia() {

    override fun mapToMedia(): Media {
        return Media(id, name, mediaType.orEmpty(), posterPath.orEmpty())
    }
}
