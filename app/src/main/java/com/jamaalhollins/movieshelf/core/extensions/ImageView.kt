package com.jamaalhollins.movieshelf.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jamaalhollins.movieshelf.R

@BindingAdapter("app:imageUrl")
fun ImageView.setImage(url: String) {
    Glide.with(this.context)
        .load(url.ifEmpty { null })
        .error(R.drawable.ic_baseline_media_48)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}