package com.jamaalhollins.movieshelf.core.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String?,
    val posterPath: String?,
    val genres: List<Genre>,
    val homepage: String,
    val releaseDate: String
)
