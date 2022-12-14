package com.mediashelf.android.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.presentation.adapter.media.MediaComparator
import com.mediashelf.android.core.presentation.adapter.media.OnMediaItemClickedListener
import com.mediashelf.android.databinding.ListItemSearchMediaBinding

class SearchMediaPagingAdapter(private val listener: OnMediaItemClickedListener) :
    PagingDataAdapter<Media, SearchMediaPagingAdapter.SearchMediaViewHolder>(MediaComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMediaViewHolder {
        val binding =
            ListItemSearchMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchMediaViewHolder(binding, listener)
    }


    class SearchMediaViewHolder(
        private val binding: ListItemSearchMediaBinding,
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

    override fun onBindViewHolder(holder: SearchMediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}