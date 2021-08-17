package com.sagrishin.uikit.utils

import android.content.res.ColorStateList
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.Px
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.appbar.AppBarLayout
import com.sagrishin.common.utils.toDp

fun View.canScrollDown(): Boolean = canScrollVertically(1)

fun View.canScrollUp(): Boolean = canScrollVertically(-1)

var List<View>.isVisible: Boolean
    get() { throw IllegalAccessException("This property hasn't get accessor") }
    set(value) { forEach { it.isVisible = value } }

var List<View>.isGone: Boolean
    get() { throw IllegalAccessException("This property hasn't get accessor") }
    set(value) { forEach { it.isGone = value } }


fun ViewGroup.inflate(@LayoutRes l: Int): View = LayoutInflater.from(context).inflate(l, this, false)


inline fun List<View>.setSafeOnClickListener(crossinline function: (View?) -> Unit) {
    forEach { it.setSafeOnClickListener(function) }
}

fun View.setRadius(@Px radius: Int, @ColorRes backgroundColor: Int? = null) {
    background = GradientDrawable().apply {
        backgroundColor?.let {
            color = ColorStateList.valueOf(com.sagrishin.common.utils.getColor(context, backgroundColor))
        }
        cornerRadius = context.toDp(radius).toFloat()
    }
}

fun EditText.onImeActionListener(actionId: Int, function: () -> Unit) {
    setOnEditorActionListener { _, id, _ ->
        if (actionId == id) {
            function()
            return@setOnEditorActionListener true
        }

        false
    }
}


inline fun  View.afterMeasured(crossinline f: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if ((measuredWidth > 0) && (measuredHeight > 0)) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}


val AppBarLayout.isExpanded: Boolean
    get() = (height - bottom) == 0


fun View.getRelativePosition(): Pair<Int, Int> {
    val offsetViewBounds = Rect()
    getDrawingRect(offsetViewBounds)
    (parent as ViewGroup).offsetDescendantRectToMyCoords(this, offsetViewBounds)
    return offsetViewBounds.left to offsetViewBounds.top
}