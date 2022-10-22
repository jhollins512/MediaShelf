package com.mediashelf.android.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mediashelf.android.core.domain.model.WatchProviderWithViewingOptions
import com.mediashelf.android.databinding.ListItemWatchProviderWithOptionsBinding

class WatchProviderWithViewingOptionsAdapter :
    ListAdapter<WatchProviderWithViewingOptions, WatchProviderWithViewingOptionsAdapter.WatchProviderWithViewingOptionsViewHolder>(
        WatchProviderWithViewingOptionsComparator
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WatchProviderWithViewingOptionsViewHolder {
        val binding =
            ListItemWatchProviderWithOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WatchProviderWithViewingOptionsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WatchProviderWithViewingOptionsViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class WatchProviderWithViewingOptionsViewHolder(private val binding: ListItemWatchProviderWithOptionsBinding) :
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