package com.jamaalhollins.movieshelf.core.extensions

import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator

@BindingAdapter("app:show")
fun CircularProgressIndicator.show(show: Boolean) {
    if (show) {
        show()
    } else {
        hide()
    }
}