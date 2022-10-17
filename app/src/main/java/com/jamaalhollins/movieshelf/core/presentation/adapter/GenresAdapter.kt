package com.jamaalhollins.movieshelf.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamaalhollins.movieshelf.core.domain.model.Genre
import com.jamaalhollins.movieshelf.databinding.ListItemGenreBinding

class GenresAdapter : ListAdapter<Genre, GenresAdapter.GenreViewHolder>(GenreComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding =
            ListItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GenreViewHolder(val binding: ListItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.genreTitle.text = genre.name
        }
    }

    private object GenreComparator : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }
}