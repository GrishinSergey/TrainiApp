package com.sagrishin.uikit.utils

import android.widget.TextView
import com.sagrishin.common.utils.getDrawable

fun TextView.setStartCompoundDrawable(drawableResId: Int?) {
    val (_, d2, d3, d4) = compoundDrawables
    setCompoundDrawablesWithIntrinsicBounds(drawableResId?.let { getDrawable(context, drawableResId) }, d2, d3, d4)
}


fun TextView.setEndCompoundDrawable(drawableResId: Int?) {
    val (d1, d2, _, d4) = compoundDrawables
    val dr = drawableResId?.let { getDrawable(context, drawableResId) }
    setCompoundDrawablesWithIntrinsicBounds(d1, d2, dr, d4)
}
