package com.mediashelf.android.core.domain.model

import com.mediashelf.android.core.presentation.model.WatchProviderViewingOption

data class WatchProviderWithViewingOptions(
    val logoPath: String,
    val providerId: Int,
    val providerName: String,
    val viewOptions: List<WatchProviderViewingOption>
)