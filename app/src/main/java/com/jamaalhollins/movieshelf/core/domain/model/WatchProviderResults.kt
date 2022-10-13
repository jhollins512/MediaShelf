package com.jamaalhollins.movieshelf.core.domain.model

data class WatchProviderResults(
    val id: Int,
    val results: Map<String, WatchProviderCountry>
)