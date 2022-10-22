package com.mediashelf.android.core.domain.model

data class WatchProviderCountry(
    val link: String,
    val rent: List<WatchProvider>,
    val buy: List<WatchProvider>,
    val flatrate: List<WatchProvider>
)
