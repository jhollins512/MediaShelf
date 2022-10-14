package com.jamaalhollins.movieshelf.core.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imageUrl", "error"], requireAll = false)
fun ImageView.setImage(imageUrl: String, errorDrawable: Drawable?) {
    Glide.with(this.context)
        .load(imageUrl.ifEmpty { null })
        .error(errorDrawable)
        .centerCrop()
        .into(this)
}