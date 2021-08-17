package com.sagrishin.common.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <reified T : Any?> Fragment.bundleArgs(key: String = T::class.java.name, required: Boolean = true): Lazy<T> {
    return lazy {
        @Suppress("UNCHECKED_CAST")
        if (required) {
            requireNotNull(requireArguments()[key]) { "Required value on key '$key' not passed" } as T
        } else {
            requireArguments()[key] as T
        }
    }
}


inline fun <reified T : Any?> FragmentActivity.bundleArgs(
    key: String = T::class.java.name,
    required: Boolean = true
): Lazy<T> {
    return lazy {
        @Suppress("UNCHECKED_CAST")
        if (required) {
            requireNotNull(intent.extras!![key]) { "Required value on key '$key' not passed" } as T
        } else {
            intent.extras?.get(key) as T
        }
    }
}
