package com.sagrishin.common.navutils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addOnBackPressedCallback(fragment: Fragment, callback: () -> Unit) {
    onBackPressedDispatcher.addCallback(fragment, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() = callback()
    })
}