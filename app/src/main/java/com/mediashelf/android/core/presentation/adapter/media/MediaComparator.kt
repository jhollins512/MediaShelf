package com.mediashelf.android.core.presentation.adapter.media

import androidx.recyclerview.widget.DiffUtil
import com.mediashelf.android.core.domain.model.Media

object MediaComparator : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id && oldItem.mediaType == newItem.mediaType
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}