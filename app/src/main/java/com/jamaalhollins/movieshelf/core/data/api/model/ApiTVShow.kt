package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiTVShow(
    val name: String,
    @Json(name = "original_name") val originalName: String,
    @Json(name = "first_air_date") val firstAirDate: String,
    @Json(name = "origin_country") val originCountry: List<String>,
    @Json(name = "backdrop_path") var backdropPath: String?,
    @Json(name = "genre_ids") var genreIds: List<Int> = emptyList(),
    @Json(name = "original_language") var originalLanguage: String? = null,
    @Json(name = "poster_path") var posterPath: String? = null,
    @Json(name = "vote_average") var voteAverage: Double = 0.0,
    @Json(name = "vote_count") var voteCount: Int = 0,
    var overview: String = ""
) : ApiMedia() {

    override fun mapToMedia(): Media {
        return Media(id, name, "tv", posterPath.orEmpty())
    }
}
