package com.mediashelf.android.core.extensions

import android.content.res.Resources
import android.util.TypedValue

fun Int.dpToPx(): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
).toInt()