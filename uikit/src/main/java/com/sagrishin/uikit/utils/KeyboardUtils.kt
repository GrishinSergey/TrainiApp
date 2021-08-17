package com.sagrishin.uikit.utils

import android.app.Activity
import android.app.Service
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard(view)
}

@JvmOverloads
fun FragmentActivity.hideKeyboard(view: View? = null) {
    hideKeyboardFor((currentFocus ?: view ?: View(this)))
//    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
//        .hideSoftInputFromWindow((currentFocus ?: view ?: View(this)).windowToken, 0)
}

fun Context.hideKeyboardFor(view: View) {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view.windowToken, 0)
}


fun EditText.showSoftKeyboard(inputMethodFlag: Int = 0) {
    (context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager)
        .showSoftInput(this, inputMethodFlag)
}


fun Context.showSoftKeyboardFor(view: View) {
    (getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(view, 0)
}
