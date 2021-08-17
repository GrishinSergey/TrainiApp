package com.sagrishin.uikit.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

//fun Window.setStatusBarColor(context: Context) {
//    val hsv = FloatArray(3)
//    var color = getColor(context, android.R.color.transparent)
//    Color.colorToHSV(color, hsv)
//    hsv[2] *= 0.8f
//    color = Color.HSVToColor(hsv)
//
//    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//    statusBarColor = color
//}


fun Fragment.makeLayoutFullScreen(flag: Boolean) {
    requireActivity().makeLayoutFullScreen(flag)
}

fun FragmentActivity.makeLayoutFullScreen(flag: Boolean) {
    window.decorView.systemUiVisibility = if (flag) {
        /// View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR -- is possible to change items' color in status bar from code,
        /// fex depends on color of image or scroll state

        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    } else {
        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_VISIBLE
    }
}
