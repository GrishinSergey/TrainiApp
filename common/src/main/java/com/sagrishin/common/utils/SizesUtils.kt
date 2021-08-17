@file:JvmName("SizeUtils")

package com.sagrishin.common.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import kotlin.math.roundToInt

fun Context.toDp(number: Int): Int {
    return (number / (resources.displayMetrics.density / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}


fun Context.toDp(number: Float): Float {
    return (number / (resources.displayMetrics.density / DisplayMetrics.DENSITY_DEFAULT))
}


fun Context.toPx(number: Int): Int {
    return (number * resources.displayMetrics.density).roundToInt()
}


fun Context.toPx(number: Float): Float {
    return (number * resources.displayMetrics.density)
}


fun Context.toSp(number: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, number.toFloat(), resources.displayMetrics).roundToInt()
}


fun Context.toSp(number: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, number, resources.displayMetrics)
}
