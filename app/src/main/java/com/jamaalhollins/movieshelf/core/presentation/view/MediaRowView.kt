package com.jamaalhollins.movieshelf.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.jamaalhollins.movieshelf.R
import com.jamaalhollins.movieshelf.core.domain.model.Media
import com.jamaalhollins.movieshelf.core.extensions.dpToPx
import com.jamaalhollins.movieshelf.core.presentation.MarginItemDecoration
import com.jamaalhollins.movieshelf.core.presentation.adapter.MediaAdapter
import com.jamaalhollins.movieshelf.databinding.ViewHomeMediaRowBinding

class MediaRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewHomeMediaRowBinding.inflate(LayoutInflater.from(context), this, true)
    private var onMediaItemClickedListener: OnMediaItemClickedListener? = null

    init {
        initView(attrs)
        setupMediaList()
    }

    private fun initView(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MediaRowView)

        setTitle(attributes.getString(R.styleable.MediaRowView_mediaRow_title) ?: "")

        attributes.recycle()
    }


    private fun setTitle(title: String) {
        binding.title.text = title
    }

    private fun setupMediaList() {
        binding.mediaList.apply {
            adapter = MediaAdapter { onMediaItemClickedListener?.onMediaItemClicked(it) }
            addItemDecoration(MarginItemDecoration(end = 8.dpToPx()))
        }
    }

    fun setRowMediaList(media: List<Media>) {
        val mediaAdapter = binding.mediaList.adapter as MediaAdapter
        mediaAdapter.submitList(media)
    }

    fun setListener(listener: OnMediaItemClickedListener) {
        this.onMediaItemClickedListener = listener
    }

    fun interface OnMediaItemClickedListener {
        fun onMediaItemClicked(media: Media)
    }
}

@BindingAdapter("app:mediaList")
fun setMediaList(view: MediaRowView, media: List<Media>?) {
    media?.let {
        view.setRowMediaList(media)
    }
}

@BindingAdapter("app:setOnMediaItemClicked")
fun setOnMediaItemClicked(
    view: MediaRowView,
    listener: MediaRowView.OnMediaItemClickedListener
) {
    view.setListener(listener)
}




