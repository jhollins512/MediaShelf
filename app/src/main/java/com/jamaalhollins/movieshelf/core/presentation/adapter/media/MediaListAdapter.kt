package com.jamaalhollins.movieshelf.core.presentation.adapter.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.databinding.ListItemMediaBinding

class MediaListAdapter(private val listener: OnMediaItemClickedListener) :
    ListAdapter<Media, MediaListAdapter.MediaViewHolder>(MediaComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding =
            ListItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MediaViewHolder(
        private val binding: ListItemMediaBinding,
        private val listener: OnMediaItemClickedListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(media: Media?) {
            media?.let {
                binding.media = it
                binding.listener = listener
                binding.executePendingBindings()
            }
        }
    }
}