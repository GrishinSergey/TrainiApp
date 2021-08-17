package com.sagrishin.uikit.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupPaging(threshold: Int = 5, onLoadMore: () -> Unit) {
    val linearLayoutManager = layoutManager as LinearLayoutManager
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

            if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + threshold)) {
                onLoadMore()
            }
        }
    })
}
