package com.jamaalhollins.movieshelf.core.data.api.model
import com.jamaalhollins.movieshelf.core.domain.model.TVShowContentRating
import com.jamaalhollins.movieshelf.core.domain.model.TVShowContentRatings
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class ApiTVShowContentRatings(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val results: List<ApiTVShowContentRating>
){
    fun mapToDomain() : TVShowContentRatings{
        return TVShowContentRatings(id,results.map { it.mapToDomain() })
    }
}

@JsonClass(generateAdapter = true)
data class ApiTVShowContentRating(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "rating")
    val rating: String
){
    fun mapToDomain() : TVShowContentRating{
        return TVShowContentRating(rating)
    }
}