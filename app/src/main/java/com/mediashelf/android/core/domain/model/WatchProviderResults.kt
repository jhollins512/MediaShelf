package com.mediashelf.android.core.domain.model

data class WatchProviderResults(
    val id: Int,
    val results: Map<String, WatchProviderCountry>
)