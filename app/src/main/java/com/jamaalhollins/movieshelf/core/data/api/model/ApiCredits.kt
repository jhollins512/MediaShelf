package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Cast
import com.jamaalhollins.movieshelf.core.domain.model.Credits
import com.jamaalhollins.movieshelf.core.domain.model.Crew
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiCredits(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cast")
    val cast: List<ApiCast>,
    @Json(name = "crew")
    val crew: List<ApiCrew>
) {
    fun mapToDomain(): Credits {
        return (Credits(id, cast.map { it.mapToDomain() }, crew.map { it.mapToDomain() }))
    }
}

@JsonClass(generateAdapter = true)
data class ApiCast(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "cast_id")
    val castId: Int? = null,
    @Json(name = "character")
    val character: String?,
    @Json(name = "credit_id")
    val creditId: String?,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "known_for_department")
    val knownForDepartment: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "order")
    val order: Int,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String?
) {
    fun mapToDomain(): Cast {
        return (Cast(id, name.orEmpty(), order))
    }
}

@JsonClass(generateAdapter = true)
data class ApiCrew(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "credit_id")
    val creditId: String?,
    @Json(name = "department")
    val department: String?,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "job")
    val job: String?,
    @Json(name = "known_for_department")
    val knownForDepartment: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String?
) {
    fun mapToDomain(): Crew {
        return (Crew(id, job.orEmpty(), name.orEmpty()))
    }
}