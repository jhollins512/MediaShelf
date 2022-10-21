package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovie(
    val title: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "release_date") val releaseDate: String,
    val video: Boolean,
    @Json(name = "backdrop_path") var backdropPath: String?,
    @Json(name = "genre_ids") var genreIds: List<Int>,
    @Json(name = "original_language") var originalLanguage: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "vote_average") var voteAverage: Double,
    @Json(name = "vote_count") var voteCount: Int,
    var adult: Boolean = false
) : ApiMedia() {

    override fun mapToMedia(): Media {
        return Media(id, title, "movie", posterPath.orEmpty())
    }
}
