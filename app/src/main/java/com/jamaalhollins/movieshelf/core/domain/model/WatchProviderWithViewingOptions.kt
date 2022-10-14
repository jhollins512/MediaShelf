package com.jamaalhollins.movieshelf.core.domain.model

import com.jamaalhollins.movieshelf.core.presentation.model.WatchProviderViewingOption

data class WatchProviderWithViewingOptions(
    val logoPath: String,
    val providerId: Int,
    val providerName: String,
    val viewOptions: List<WatchProviderViewingOption>
)