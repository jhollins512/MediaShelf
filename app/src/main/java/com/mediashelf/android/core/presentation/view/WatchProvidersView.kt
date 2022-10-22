package com.mediashelf.android.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.mediashelf.android.R
import com.mediashelf.android.core.domain.model.Media
import com.mediashelf.android.core.domain.model.WatchProviderWithViewingOptions
import com.mediashelf.android.core.extensions.dpToPx
import com.mediashelf.android.core.presentation.MarginItemDecoration
import com.mediashelf.android.core.presentation.adapter.WatchProviderWithViewingOptionsAdapter
import com.mediashelf.android.databinding.ViewWatchProvidersBinding

class WatchProvidersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        ViewWatchProvidersBinding.inflate(LayoutInflater.from(context), this, true)

    private var onAttributionClicked: OnAttributionClicked? = null

    init {
        initView(attrs)
        setupWatchProviderList()
        setupJustWatchLink()
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

    private fun setupJustWatchLink() {
        binding.attributionText.setOnClickListener { onAttributionClicked?.onAttributionClicked() }
    }

    fun setWatchProvidersList(media: List<WatchProviderWithViewingOptions>) {
        val adapter = binding.watchProvidersList.adapter as WatchProviderWithViewingOptionsAdapter
        adapter.submitList(media)
    }

    fun setOnAttributionClickListener(listener: OnAttributionClicked) {
        onAttributionClicked = listener
    }

    fun interface OnAttributionClicked {
        fun onAttributionClicked()
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

@BindingAdapter("app:onAttributionClicked")
fun setOnAttributionClicked(
    view: WatchProvidersView,
    listener: WatchProvidersView.OnAttributionClicked
) {
    view.setOnAttributionClickListener(listener)
}
