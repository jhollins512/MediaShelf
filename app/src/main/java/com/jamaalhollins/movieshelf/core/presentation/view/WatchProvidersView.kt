package com.jamaalhollins.movieshelf.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.jamaalhollins.movieshelf.R
import com.jamaalhollins.movieshelf.core.domain.model.WatchProviderWithViewingOptions
import com.jamaalhollins.movieshelf.core.extensions.dpToPx
import com.jamaalhollins.movieshelf.core.presentation.MarginItemDecoration
import com.jamaalhollins.movieshelf.core.presentation.adapter.WatchProviderWithViewingOptionsAdapter
import com.jamaalhollins.movieshelf.databinding.ViewWatchProvidersBinding

class WatchProvidersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewWatchProvidersBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initView(attrs)
        setupWatchProviderList()
    }

    private fun initView(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.WatchProvidersView)

        setTitle(attributes.getString(R.styleable.WatchProvidersView_watchProviders_title) ?: "")

        attributes.recycle()
    }

    private fun setTitle(title: String) {
        binding.title.text = title
    }

    private fun setupWatchProviderList() {
        binding.watchProvidersList.apply {
            adapter = WatchProviderWithViewingOptionsAdapter()
            addItemDecoration(
                MarginItemDecoration(
                    bottom = 4.dpToPx()
                )
            )
        }
    }

    fun setWatchProvidersList(media: List<WatchProviderWithViewingOptions>) {
        val adapter = binding.watchProvidersList.adapter as WatchProviderWithViewingOptionsAdapter
        adapter.submitList(media)
    }
}

@BindingAdapter("app:watchProvidersList")
fun setWatchProviders(
    view: WatchProvidersView,
    watchProviders: List<WatchProviderWithViewingOptions>?
) {
    watchProviders?.let {
        view.setWatchProvidersList(watchProviders)
    }
}