package com.mediashelf.android.core.data.api.model

import com.mediashelf.android.core.domain.model.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGenre(
    @Json(name = "id") val id: Int, @Json(name = "name") val name: String
) {
    fun mapToDomain(): Genre {
        return Genre(id, name)
    }
}
