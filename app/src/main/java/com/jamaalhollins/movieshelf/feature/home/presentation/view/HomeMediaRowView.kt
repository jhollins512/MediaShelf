package com.jamaalhollins.movieshelf.feature.home.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.jamaalhollins.movieshelf.R
import com.jamaalhollins.movieshelf.core.extensions.dpToPx
import com.jamaalhollins.movieshelf.core.presentation.MarginItemDecoration
import com.jamaalhollins.movieshelf.core.presentation.adapter.MediaAdapter
import com.jamaalhollins.movieshelf.core.presentation.model.Media
import com.jamaalhollins.movieshelf.databinding.ViewHomeMediaRowBinding

class HomeMediaRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewHomeMediaRowBinding.inflate(LayoutInflater.from(context), this, true)
    private var listener: Listener? = null

    init {
        initView(attrs)
        setupMediaList()
    }

    private fun initView(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.HomeMediaRowView)

        setTitle(attributes.getString(R.styleable.HomeMediaRowView_homeMediaRow_title) ?: "")

        attributes.recycle()
    }

    private fun setupMediaList() {
        binding.mediaList.apply {
            adapter = MediaAdapter()
            addItemDecoration(MarginItemDecoration(end = 8.dpToPx()))
        }
    }

    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun setRowMediaList(media: List<Media>) {
        val mediaAdapter = binding.mediaList.adapter as MediaAdapter
        mediaAdapter.submitList(media)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun interface Listener {
        fun onMoreClicked()
    }
}

@BindingAdapter("app:mediaList")
fun HomeMediaRowView.setMediaList(media: List<Media>?) {
    media?.let {
        setRowMediaList(media)
    }
}



