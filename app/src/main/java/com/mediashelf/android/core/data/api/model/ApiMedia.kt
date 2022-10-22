package com.mediashelf.android.core.data.api.model

import com.mediashelf.android.core.domain.model.Media
import com.squareup.moshi.Json

sealed class ApiMedia {
    var id: Int = -1

    @Json(name = "media_type")
    var mediaType: String? = null

    var popularity: Double = 0.0

    abstract fun mapToMedia() : Media
}
