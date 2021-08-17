package com.sagrishin.common.livedatas

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

operator fun <T> LiveData<out List<T>>.get(pos: Int): T {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!![pos]
}

inline fun <T> LiveData<out List<T>>.forEach(crossinline lambda: (T) -> Unit) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.forEach(lambda)
}

inline fun <T> LiveData<out List<T>>.forEachIndexed(crossinline lambda: (Int, T) -> Unit) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.forEachIndexed(lambda)
}

operator fun <T> LiveData<out MutableList<T>>.set(pos: Int, newValue: T) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!![pos] = newValue
}

operator fun <K, V> LiveData<out MutableMap<K, V>>.set(key: K, newValue: V) {
    requireNotNull(value) { "Map in LiveData can't be null" }
    value!![key] = newValue
}

operator fun <K, V> LiveData<out MutableMap<K, V>>.get(key: K): V? {
    requireNotNull(value) { "Map in LiveData can't be null" }
    return value!![key]
}

fun <K, V> LiveData<out MutableMap<K, V>>.getOrThrow(key: K): V {
    requireNotNull(value) { "Map in LiveData can't be null" }
    return value!!.getValue(key)
}

fun <T> MutableLiveData<T>.refresh(newValue: T? = value) {
    if (Looper.getMainLooper().thread == Thread.currentThread()) {
        value = newValue
    } else {
        postValue(newValue)
    }
}

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(list: List<T>) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!! += list
}

fun <T> MutableLiveData<MutableList<T>>.addAllTo(index: Int, list: List<T>) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.addAll(index, list)
}

fun <T> MutableLiveData<MutableList<T>>.addAll(list: List<T>) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.addAll(list)
}

fun <T> MutableLiveData<MutableList<T>>.addTo(index: Int, item: T) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.add(index, item)
}

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: T) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!! += item
}

fun <T> LiveData<MutableList<T>>.removeAt(position: Int) {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.removeAt(position)
}


fun <T> LiveData<out List<T>>.isEmpty(): Boolean {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.isEmpty()
}

fun <T> LiveData<out List<T>>.isNotEmpty(): Boolean {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.isNotEmpty()
}

operator fun <T> LiveData<out List<T>>.contains(item: T): Boolean {
    requireNotNull(value) { "List in LiveData can't be null" }
    return item in value!!
}


val <T> LiveData<out List<T>>.lastIndex: Int
    get() {
        requireNotNull(value) { "List in LiveData can't be null" }
        return value!!.lastIndex
    }

val <T> LiveData<out List<T>>.size: Int
    get() {
        requireNotNull(value) { "List in LiveData can't be null" }
        return value!!.size
    }
