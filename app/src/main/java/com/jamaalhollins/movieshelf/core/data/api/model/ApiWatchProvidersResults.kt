package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.WatchProvider
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderCountry
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderResults
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiWatchProvidersResults(
    val id: Int,
    val results: Map<String, ApiWatchProvidersCountry>
) {
    fun mapToDomain(): WatchProviderResults {
        return WatchProviderResults(id, results.mapValues { it.value.mapToDomain() })
    }
}

@JsonClass(generateAdapter = true)
data class ApiWatchProvidersCountry(
    val link: String,
    val rent: List<ApiWatchProvider>?,
    val buy: List<ApiWatchProvider>?,
    val flatrate: List<ApiWatchProvider>?
) {
    fun mapToDomain(): WatchProviderCountry {
        return WatchProviderCountry(
            link,
            rent?.map { it.mapToDomain() } ?: emptyList(),
            buy?.map { it.mapToDomain() } ?: emptyList(),
            flatrate?.map { it.mapToDomain() } ?: emptyList()
        )
    }
}

@JsonClass(generateAdapter = true)
data class ApiWatchProvider(
    @Json(name = "display_priority") val displayPriority: Int,
    @Json(name = "logo_path") val logoPath: String,
    @Json(name = "provider_id") val providerId: Int,
    @Json(name = "provider_name") val providerName: String
) {
    fun mapToDomain(): WatchProvider {
        return WatchProvider(displayPriority, logoPath, providerId, providerName)
    }
}