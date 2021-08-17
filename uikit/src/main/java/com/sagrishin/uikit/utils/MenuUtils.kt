package com.sagrishin.uikit.utils

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes

inline operator fun Menu.get(@IdRes id: Int): MenuItem = findItem(id)


inline fun MenuItem.setSafeOnClickListener(crossinline function: () -> Unit) {
    val safeClickListener = SafeClickListener { function() }
    setOnMenuItemClickListener {
        safeClickListener.onClick(actionView)
        true
    }
}
