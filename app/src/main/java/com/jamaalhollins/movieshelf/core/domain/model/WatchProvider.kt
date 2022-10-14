package com.jamaalhollins.movieshelf.core.domain.model

data class WatchProvider(
    val displayPriority: Int,
    val logoPath: String,
    val providerId: Int,
    val providerName: String
)