package com.jamaalhollins.movieshelf.core.domain.model

import com.jamaalhollins.movieshelf.core.data.api.model.ApiMovieReleaseDate

data class MovieReleaseDatesResult(
    val iso31661: String,
    val releaseDates: List<MovieReleaseDate>
)
