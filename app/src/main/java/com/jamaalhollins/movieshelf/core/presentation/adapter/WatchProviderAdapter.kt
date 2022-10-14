package com.jamaalhollins.movieshelf.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderWithViewingOptions
import com.jamaalhollins.movieshelf.databinding.ListItemWatchProviderBinding

class WatchProviderAdapter :
    ListAdapter<WatchProviderWithViewingOptions, WatchProviderAdapter.WatchProviderWithViewingOptionsViewHolder>(
        WatchProviderWithViewingOptionsComparator
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WatchProviderWithViewingOptionsViewHolder {
        val binding =
            ListItemWatchProviderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchProviderWithViewingOptionsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WatchProviderWithViewingOptionsViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class WatchProviderWithViewingOptionsViewHolder(private val binding: ListItemWatchProviderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(watchProvider: WatchProviderWithViewingOptions) {
            binding.watchProvider = watchProvider
            binding.executePendingBindings()
        }
    }

    private object WatchProviderWithViewingOptionsComparator :
        DiffUtil.ItemCallback<WatchProviderWithViewingOptions>() {

        override fun areItemsTheSame(
            oldItem: WatchProviderWithViewingOptions,
            newItem: WatchProviderWithViewingOptions
        ): Boolean {
            return oldItem.providerId == newItem.providerId
        }

        override fun areContentsTheSame(
            oldItem: WatchProviderWithViewingOptions,
            newItem: WatchProviderWithViewingOptions
        ): Boolean {
            return oldItem == newItem
        }
    }
}