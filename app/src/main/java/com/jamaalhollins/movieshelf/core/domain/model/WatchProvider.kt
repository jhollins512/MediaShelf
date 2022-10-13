package com.jamaalhollins.movieshelf.core.domain.model

data class WatchProvider(
    val displayPriority: Int,
    val logoPath: String,
    val providerId: Int,
    val providerName: String
) {
    override fun equals(other: Any?): Boolean {
        return other is WatchProvider && providerId == other.providerId
    }

    override fun hashCode(): Int {
        return providerId.hashCode()
    }
}