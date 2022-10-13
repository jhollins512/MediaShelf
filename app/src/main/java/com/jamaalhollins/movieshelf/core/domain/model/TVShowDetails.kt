package com.jamaalhollins.movieshelf.core.domain.model

data class TVShowDetails(
    val id: Int,
    val backdropPath: String?,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val name: String,
    val networks: List<Network>
)