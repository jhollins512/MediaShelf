package com.jamaalhollins.movieshelf.core.domain.model

import com.jamaalhollins.movieshelf.core.data.api.model.ApiTVShowContentRating

data class TVShowContentRatings(
    val id: Int,
    val results: List<TVShowContentRating>
)