package com.mediashelf.android.core.data.api.model

import com.mediashelf.android.core.domain.model.MovieReleaseDate
import com.mediashelf.android.core.domain.model.MovieReleaseDates
import com.mediashelf.android.core.domain.model.MovieReleaseDatesResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovieReleaseDates(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val results: List<ApiMovieReleaseDatesResult>
) {
    fun mapToDomain(): MovieReleaseDates {
        return MovieReleaseDates(id, results.map { it.mapToDomain() })
    }
}

@JsonClass(generateAdapter = true)
data class ApiMovieReleaseDatesResult(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "release_dates")
    val releaseDates: List<ApiMovieReleaseDate>
) {
    fun mapToDomain(): MovieReleaseDatesResult {
        return MovieReleaseDatesResult(iso31661, releaseDates.map { it.mapToDomain() })
    }
}

@JsonClass(generateAdapter = true)
data class ApiMovieReleaseDate(
    @Json(name = "certification")
    val certification: String,
    @Json(name = "iso_639_1")
    val iso6391: String?,
    @Json(name = "note")
    val note: String?,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "type")
    val type: Int
) {
    fun mapToDomain(): MovieReleaseDate {
        return MovieReleaseDate(certification, type)
    }
}