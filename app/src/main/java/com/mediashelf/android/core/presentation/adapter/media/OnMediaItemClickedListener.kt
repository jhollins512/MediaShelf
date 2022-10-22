package com.mediashelf.android.core.presentation.adapter.media

import com.mediashelf.android.core.domain.model.Media

fun interface OnMediaItemClickedListener {
    fun onMediaItemClicked(media: Media)
}