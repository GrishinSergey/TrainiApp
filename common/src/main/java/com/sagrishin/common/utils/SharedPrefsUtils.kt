package com.sagrishin.common.utils

import android.content.SharedPreferences.Editor

operator fun <T> Editor.set(key: String, value: T) {
    when (value) {
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        is String -> putString(key, value)
        is Boolean -> putBoolean(key, value)
    }
}
