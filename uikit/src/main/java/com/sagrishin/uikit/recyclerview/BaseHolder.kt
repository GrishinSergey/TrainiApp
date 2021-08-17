package com.sagrishin.uikit.recyclerview

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.recyclerview.widget.RecyclerView
import com.sagrishin.common.utils.toDp
import com.sagrishin.common.utils.toPx
import com.sagrishin.common.utils.toSp
import com.sagrishin.uikit.utils.inflate

abstract class BaseHolder<T> : RecyclerView.ViewHolder {

    val context: Context
        get() = itemView.context

    constructor(v: View) : super(v)

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup) : super(parent.inflate(layoutRes))

    open fun onCreate() {
    }

    open fun onBind(item: T) {
    }

    open fun onBind(pos: Int, itemCount: Int, item: T) {
        onBind(item)
    }

    open fun onBind(pos: Int, itemCount: Int, item: T, payloads: MutableList<Any>) {
    }

    fun getFont(@FontRes fontId: Int): Typeface {
        return com.sagrishin.common.utils.getFont(context, fontId)
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable {
        return com.sagrishin.common.utils.getDrawable(context, resId)
    }

    fun getColor(@ColorRes resId: Int): Int {
        return com.sagrishin.common.utils.getColor(context, resId)
    }

    fun getString(@StringRes resId: Int, vararg params: Any): String {
        return context.getString(resId, *params)
    }

    fun getPluralBy(@PluralsRes resId: Int, count: Int): String {
        return com.sagrishin.common.utils.getPlural(context, resId, count)
    }

    fun Int.toPx(): Int {
        return context.toPx(this)
    }

    fun Int.toDb(): Int {
        return context.toDp(this)
    }

    fun Int.toSp(): Int {
        return context.toSp(this)
    }

    fun Float.toPx(): Float {
        return context.toPx(this)
    }

    fun Float.toDb(): Float {
        return context.toDp(this)
    }

    fun Float.toSp(): Float {
        return context.toSp(this)
    }

}