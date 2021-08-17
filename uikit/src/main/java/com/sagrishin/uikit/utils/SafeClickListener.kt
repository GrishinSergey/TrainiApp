package com.sagrishin.uikit.utils

import android.os.SystemClock
import android.view.View

/**
 * Prevents multi click on view during [defaultInterval], so when user taps quickly, only
 * first tap will be detected and passed to handle, other taps will be skipped
 */
class SafeClickListener(
    private val defaultInterval: Int = 500,
    private val onSafeCLick: (View?) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }

}

inline fun View.setSafeOnClickListener(crossinline onSafeClick: (View?) -> Unit) {
    setOnClickListener(SafeClickListener { onSafeClick(it) })
}
