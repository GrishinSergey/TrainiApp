package com.sagrishin.common.navutils

import androidx.annotation.IdRes
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph

fun getParentNavGraphFrom(destination: NavDestination): NavGraph? {
    return destination.parent?.let { internalGetParentNavGraphFrom(destination.parent!!) }
}


private tailrec fun internalGetParentNavGraphFrom(parent: NavGraph): NavGraph {
    return if (parent.parent == null) parent else internalGetParentNavGraphFrom(parent.parent!!)
}


fun NavController.findBackStackEntry(@IdRes destinationId: Int): NavBackStackEntry? {
    return try {
        getBackStackEntry(destinationId)
    } catch (e: IllegalArgumentException) {
        null
    }
}
