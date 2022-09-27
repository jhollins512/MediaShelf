package com.jamaalhollins.movieshelf.core.data.api.model

import com.squareup.moshi.Json

sealed class ApiMedia {
    var adult: Boolean = false

    @Json(name = "backdrop_path")
    var backdropPath: String? = null

    @Json(name = "genre_ids")
    var genreIds: List<Int> = emptyList()

    var id: Int = -1

    @Json(name = "media_type")
    var mediaType: String? = null

    @Json(name = "original_language")
    var originalLanguage: String? = null

    var overview: String = ""

    var popularity: Double = 0.0

    @Json(name = "poster_path")
    var posterPath: String? = null

    @Json(name = "vote_average")
    var voteAverage: Double = 0.0

    @Json(name = "vote_count")
    var voteCount: Int = 0
}
