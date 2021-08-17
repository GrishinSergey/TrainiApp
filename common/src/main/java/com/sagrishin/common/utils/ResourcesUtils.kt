package com.sagrishin.common.utils

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat

fun getDrawable(context: Context, @DrawableRes id: Int, theme: Resources.Theme? = null): Drawable {
    return requireNotNull(ResourcesCompat.getDrawable(context.resources, id, theme))
}

@ColorInt
fun getColor(context: Context, @ColorRes id: Int, theme: Resources.Theme? = null): Int {
    return ResourcesCompat.getColor(context.resources, id, theme)
}

fun getColorStateList(context: Context, @ColorRes id: Int, theme: Resources.Theme? = null): ColorStateList {
    return requireNotNull(ResourcesCompat.getColorStateList(context.resources, id, theme))
}

fun getFont(context: Context, @FontRes id: Int): Typeface {
    return requireNotNull(ResourcesCompat.getFont(context, id))
}

fun getPlural(context: Context, @PluralsRes id: Int, count: Int): String {
    return requireNotNull(context.resources.getQuantityString(id, count, count))
}

fun getStringArray(context: Context, @ArrayRes id: Int): Array<String> {
    return requireNotNull(context.resources.getStringArray(id))
}
