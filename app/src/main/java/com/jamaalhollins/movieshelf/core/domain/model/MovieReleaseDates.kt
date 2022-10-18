package com.jamaalhollins.movieshelf.core.domain.model

import com.jamaalhollins.movieshelf.core.data.api.model.ApiMovieReleaseDatesResult

data class MovieReleaseDates(
    val id: Int, val results: List<ApiMovieReleaseDatesResult>
)