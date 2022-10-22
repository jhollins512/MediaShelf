package com.mediashelf.android.core.data.api.model

import com.mediashelf.android.core.domain.model.TVShowContentRating
import com.mediashelf.android.core.domain.model.TVShowContentRatings
import com.squareup.moshi.Json

data class ApiTVShowContentRatings(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val contentRatings: List<ApiTVShowContentRating>
) {
    fun mapToDomain(): TVShowContentRatings {
        return TVShowContentRatings(id, contentRatings.map { it.mapToDomain() })
    }
}

data class ApiTVShowContentRating(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "rating")
    val rating: String
) {
    fun mapToDomain(): TVShowContentRating {
        return TVShowContentRating(iso31661, rating)
    }
}