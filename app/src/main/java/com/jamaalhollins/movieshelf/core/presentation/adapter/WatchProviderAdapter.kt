package com.jamaalhollins.movieshelf.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamaalhollins.movieshelf.core.domain.model.WatchProvider
import com.jamaalhollins.movieshelf.core.extensions.setImage
import com.jamaalhollins.movieshelf.core.presentation.model.WatchProviderType
import com.jamaalhollins.movieshelf.core.utils.TMDBImageHelper
import com.jamaalhollins.movieshelf.databinding.ListItemWatchProviderBinding

class WatchProviderAdapter :
    ListAdapter<Pair<WatchProvider, List<WatchProviderType>>, WatchProviderAdapter.WatchProviderViewHolder>(
        WatchProviderComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchProviderViewHolder {
        val binding =
            ListItemWatchProviderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchProviderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WatchProviderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WatchProviderViewHolder(private val binding: ListItemWatchProviderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(providerPair: Pair<WatchProvider, List<WatchProviderType>>) {
            binding.watchProviderLogo.setImage(
                TMDBImageHelper.W500.getTMDBImageUrl(providerPair.first.logoPath),
                null
            )
            binding.watchProviderTitle.text = providerPair.first.providerName
        }
    }

    private object WatchProviderComparator :
        DiffUtil.ItemCallback<Pair<WatchProvider, List<WatchProviderType>>>() {

        override fun areItemsTheSame(
            oldItem: Pair<WatchProvider, List<WatchProviderType>>,
            newItem: Pair<WatchProvider, List<WatchProviderType>>
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Pair<WatchProvider, List<WatchProviderType>>,
            newItem: Pair<WatchProvider, List<WatchProviderType>>
        ): Boolean {
            return oldItem == newItem
        }
    }
}