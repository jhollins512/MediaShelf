package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.squareup.moshi.Json

sealed class ApiMedia {
    var id: Int = -1

    @Json(name = "media_type")
    var mediaType: String? = null

    var popularity: Double = 0.0

    abstract fun mapToMedia() : Media
}
