package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPerson(
    @Json(name = "gender") val gender: Int,
    @Json(name = "known_for") val knownFor: List<ApiMedia>,
    @Json(name = "known_for_department") val knownForDepartment: String,
    @Json(name = "name") val name: String,
    @Json(name = "profile_path") val profilePath: String?
) : ApiMedia() {

    override fun mapToMedia(): Media {
        return Media(id, name, "person", profilePath.orEmpty())
    }
}

