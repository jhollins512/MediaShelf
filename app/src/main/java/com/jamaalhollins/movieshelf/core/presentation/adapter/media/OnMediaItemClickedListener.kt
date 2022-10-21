package com.jamaalhollins.movieshelf.core.presentation.adapter.media

import com.jamaalhollins.movieshelf.core.domain.model.Media

fun interface OnMediaItemClickedListener {
    fun onMediaItemClicked(media: Media)
}