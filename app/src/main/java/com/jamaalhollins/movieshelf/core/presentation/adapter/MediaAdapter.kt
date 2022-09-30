package com.jamaalhollins.movieshelf.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.databinding.ListItemMediaBinding

class MediaAdapter(private val listener: MediaAdapter.Listener) :
    ListAdapter<Media, MediaAdapter.MediaViewHolder>(MediaComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding =
            ListItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MediaViewHolder(private val binding: ListItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(media: Media) {
            binding.media = media
            binding.listener = this@MediaAdapter.listener
            binding.executePendingBindings()
        }
    }

    fun interface Listener {
        fun onMediaItemClicked(media: Media)
    }

    private object MediaComparator : DiffUtil.ItemCallback<Media>() {
        override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
            return oldItem == newItem
        }
    }
}