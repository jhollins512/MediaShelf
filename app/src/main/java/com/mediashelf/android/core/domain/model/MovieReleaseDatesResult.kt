package com.mediashelf.android.core.domain.model

data class MovieReleaseDatesResult(
    val iso31661: String,
    val releaseDates: List<MovieReleaseDate>
)
