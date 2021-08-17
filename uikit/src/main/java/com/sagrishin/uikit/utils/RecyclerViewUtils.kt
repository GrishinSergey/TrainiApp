package com.sagrishin.uikit.utils

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sagrishin.uikit.recyclerview.BaseRecyclerViewAdapter
import com.sagrishin.uikit.recyclerview.HolderGenerator
import com.sagrishin.uikit.recyclerview.MultiTypeAdapter
import com.sagrishin.uikit.recyclerview.SingleTypeAdapter

@Suppress("UNCHECKED_CAST")
inline fun <reified T> RecyclerView.getMultiTypeAdapter(): MultiTypeAdapter<T> {
    require(adapter is MultiTypeAdapter<*>) { "Adapter must be non-null instance of type MultiTypeAdapter" }
    return adapter as MultiTypeAdapter<T>
}


inline fun <reified T> adapter(
    items: List<T> = listOf(),
    diffUtilCallback: DiffUtil.ItemCallback<T>,
    noinline generator: HolderGenerator<T>
): BaseRecyclerViewAdapter<T> {
    return SingleTypeAdapter(items.toMutableList(), diffUtilCallback).apply { holder(generator) }
}


val RecyclerView.isNotEmpty: Boolean
    get() = itemCount != 0


val RecyclerView.isEmpty: Boolean
    get() = itemCount == 0


val RecyclerView.itemCount: Int
    get() = adapter?.itemCount ?: 0


fun RecyclerView.disableUpdateItemAnimation() {
    val animator = itemAnimator
    if (animator is SimpleItemAnimator) {
        animator.supportsChangeAnimations = false
    }
}


fun RecyclerView.scrollToEnd() {
    layoutManager?.scrollToPosition((adapter?.itemCount ?: 1) - 1)
}


fun RecyclerView.scrollTo(position: Int) {
    layoutManager?.scrollToPosition(position)
}
