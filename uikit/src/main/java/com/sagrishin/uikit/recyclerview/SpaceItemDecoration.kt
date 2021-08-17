package com.sagrishin.uikit.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration constructor(
    context: Context,
    spaceResId: Int? = null,
    private var orientation: Int = LinearLayout.HORIZONTAL,
    private val space: Int = spaceResId?.let { context.resources.getDimensionPixelSize(spaceResId) } ?: 0,
    private var needShowFirstDivider: Boolean = false,
    private var needShowLastDivider: Boolean = false,
): ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (space == 0) {
            return
        }

        if (orientation == -1) {
            require(parent.layoutManager is LinearLayoutManager) {
                "DividerItemDecoration can only be used with a LinearLayoutManager"
            }
            orientation = (parent.layoutManager as LinearLayoutManager).orientation
        }

        val position = parent.getChildAdapterPosition(view)

        if ((RecyclerView.NO_POSITION == position) || (0 == position) && !needShowFirstDivider) {
            return
        }

        if (LinearLayoutManager.VERTICAL == orientation) {
            outRect.top = space
            if (needShowLastDivider && (position == (state.itemCount - 1))) {
                outRect.bottom = outRect.top
            }
        } else {
            outRect.left = space
            if (needShowLastDivider && (position == (state.itemCount - 1))) {
                outRect.right = outRect.left
            }
        }
    }

}
