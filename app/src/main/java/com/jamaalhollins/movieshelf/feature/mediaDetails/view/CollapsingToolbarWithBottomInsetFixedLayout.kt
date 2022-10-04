package com.jamaalhollins.movieshelf.feature.mediaDetails.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import timber.log.Timber
import java.util.*

class CollapsingToolbarWithBottomInsetFixedLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CollapsingToolbarLayout(context, attrs, defStyle) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var currentHeightMeasureSpec = heightMeasureSpec
        super.onMeasure(widthMeasureSpec, currentHeightMeasureSpec)
        try {
            val fs =
                Objects.requireNonNull(this.javaClass.superclass).getDeclaredField("lastInsets")
            fs.isAccessible = true
            val mLastInsets = fs[this] as WindowInsetsCompat
            val mode = MeasureSpec.getMode(currentHeightMeasureSpec)
            val topInset = mLastInsets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            if (mode == MeasureSpec.UNSPECIFIED && topInset > 0) {
                // fix the bottom empty padding
                currentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    measuredHeight - topInset, MeasureSpec.EXACTLY
                )
                super.onMeasure(widthMeasureSpec, currentHeightMeasureSpec)
            }
        } catch (e: Exception) {
            Timber.e("CollapsingToolbarWithBottomInsetFixedLayout")
        }
    }
}