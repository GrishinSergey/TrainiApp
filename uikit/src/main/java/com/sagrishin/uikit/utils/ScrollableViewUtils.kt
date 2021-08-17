package com.sagrishin.uikit.utils

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun RecyclerView.setupFabOnScrollVisibilityHandler(fab: FloatingActionButton, canShowFab: (() -> Boolean)? = null) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy <= 0) {
                fab.show()
            } else if (canShowFab?.invoke() != false) {
                fab.hide()
            }
        }
    })
}


fun AppBarLayout.setupFabOnScrollVisibilityHandler(fab: FloatingActionButton, canShowFab: (() -> Boolean)? = null) {
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
        if (verticalOffset <= 0) {
            fab.show()
        } else if (canShowFab?.invoke() != false) {
            fab.hide()
        }
    })
}
