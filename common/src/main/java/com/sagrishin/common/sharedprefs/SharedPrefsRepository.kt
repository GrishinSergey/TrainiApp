package com.sagrishin.common.sharedprefs

import android.content.SharedPreferences
import androidx.core.content.edit
import com.sagrishin.common.utils.set
import javax.inject.Inject

class SharedPrefsRepository @Inject constructor(
    private val secretPrefs: SharedPreferences
) {

    fun edit(callback: (SharedPreferences.Editor) -> Unit) {
        secretPrefs.edit { callback(this) }
    }

    fun getInt(key: String): Int? {
        return if (key !in secretPrefs) null else secretPrefs.getInt(key, -1)
    }

    fun getLong(key: String): Long? {
        return if (key !in secretPrefs) null else secretPrefs.getLong(key, -1L)
    }

    fun getFloat(key: String): Float? {
        return if (key !in secretPrefs) null else secretPrefs.getFloat(key, -1F)
    }

    fun getString(key: String): String? {
        return if (key !in secretPrefs) null else secretPrefs.getString(key, "")
    }

    fun getBoolean(key: String): Boolean? {
        return if (key !in secretPrefs) null else secretPrefs.getBoolean(key, false)
    }

    inline operator fun <reified T> get(key: String): T {
        return when (T::class.java) {
            String::class.java -> getString(key) as T
            Int::class.java -> getInt(key) as T
            Float::class.java -> getFloat(key) as T
            Long::class.java -> getLong(key) as T
            Boolean::class.java -> getBoolean(key) as T
            else -> throw IllegalStateException("${T::class.java} is not supported by ${javaClass.name}")
        }
    }

    inline operator fun <reified T> set(key: String, value: T) {
        edit { it[key] = value }
    }

    operator fun contains(key: String): Boolean {
        return key in secretPrefs
    }

    fun onSharedPrefsUpdateListener(listener: () -> Unit) {
        secretPrefs.registerOnSharedPreferenceChangeListener { _, _ -> listener() }
    }

}
