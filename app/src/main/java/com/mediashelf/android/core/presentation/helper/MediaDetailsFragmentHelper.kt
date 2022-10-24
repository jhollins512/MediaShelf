package com.mediashelf.android.core.presentation.helper

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.NestedScrollView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.elevation.ElevationOverlayProvider
import com.mediashelf.android.R
import com.mediashelf.android.core.utils.getScreenWidth
import com.mediashelf.android.core.utils.isDarkModeEnabled

fun setupPosterPosition(activity: Activity, posterImage: ImageView) {
    val marginY = (.10 * getScreenWidth(activity)).toInt()
    posterImage.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        this.topMargin = -marginY
    }
}

fun setupScrollView(
    activity: Activity,
    scrollView: NestedScrollView,
    toolbar: MaterialToolbar,
    posterImage: ImageView
) {
    scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
        val offset = posterImage.y - getActionBarSize(activity)

        setToolbarTransparent(activity as Context, toolbar, scrollY < offset)
    }
}

fun setToolbarTransparent(
    context: Context,
    toolbar: MaterialToolbar,
    transparent: Boolean
) {
    if (transparent) {
        toolbar.setBackgroundColor(
            Color.TRANSPARENT
        )
    } else {
        if (isDarkModeEnabled(context)) {
            toolbar.setBackgroundColor(
                ElevationOverlayProvider(context).compositeOverlayWithThemeSurfaceColorIfNeeded(
                    12f
                )
            )
        } else {
            toolbar.setBackgroundColor(
                ContextCompat.getColor(
                    context, R.color.primaryColor
                )
            )
        }
    }
}

private fun getActionBarSize(activity: Activity): Int {
    val typedValue = TypedValue()

    return if (activity.theme.resolveAttribute(
            androidx.appcompat.R.attr.actionBarSize, typedValue, true
        )
    ) {
        TypedValue.complexToDimensionPixelSize(typedValue.data, activity.resources.displayMetrics)
    } else {
        0
    }
}