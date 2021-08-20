package com.sagrishin.common.utils

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import java.util.function.BiPredicate

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

fun <K, V> MutableLiveData<out MutableMap<K, V>>.remove(key: K): V? {
    requireNotNull(value) { "Map in LiveData can't be null" }
    return value!!.remove(key)
}

fun <T> MutableLiveData<T>.refresh(newValue: T? = value) {
    if (Looper.getMainLooper().thread == Thread.currentThread()) {
        value = newValue
    } else {
        postValue(newValue)
    }
}

fun <T> MutableLiveData<MutableList<T>>.clear() {
    requireNotNull(value) { "List in LiveData can't be null" }
    value!!.clear()
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


fun <T> LiveData<out List<T>>.distinctUntilChanges(
    disposable: CompositeDisposable,
    predicate: BiPredicate<T, T> = BiPredicate { t, u -> t != u }
): LiveData<out List<T>> {
    val outputLiveData = MediatorLiveData<List<T>>()

    outputLiveData.addSource(this, object : Observer<List<T>> {
        private var isFirstTime = true

        override fun onChanged(currentValue: List<T>?) {
            val previousValue = outputLiveData.value

            disposable += Single.fromCallable {
                isFirstTime ||
                        ((previousValue == null) && (currentValue != null)) ||
                        ((previousValue != null) && (currentValue == null)) ||
                        ((previousValue != null) && (currentValue != null) && (previousValue to currentValue).map(predicate::test).any())
            }.subscribeOn(Schedulers.io()).subscribe({
                if (it) {
                    outputLiveData.refresh(currentValue)
                    isFirstTime = false
                }
            }, {
                /// errors here...
            })
        }
    })
    return outputLiveData
}


fun <T> LiveData<out List<T>>.isEmpty(): Boolean {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.isEmpty()
}

fun <T> LiveData<out List<T>>.lastOrNull(): T? {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.lastOrNull()
}

fun <T> LiveData<out List<T>>.last(): T {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.last()
}

fun <T> LiveData<out List<T>>.firstOrNull(): T? {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.firstOrNull()
}

fun <T> LiveData<out List<T>>.first(): T {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.first()
}

fun <T> LiveData<out List<T>>.isNotEmpty(): Boolean {
    /// in this case, value can be null as it is empty state
    return !value.isNullOrEmpty()
}

operator fun <T> LiveData<out List<T>>.contains(item: T): Boolean {
    requireNotNull(value) { "List in LiveData can't be null" }
    return item in value!!
}

fun <T> LiveData<out List<T>>.indexOfFirst(predicate: (T) -> Boolean): Int {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.indexOfFirst(predicate)
}

fun <T> LiveData<out List<T>>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.indexOfFirstOrNull(predicate)
}

fun <T> LiveData<out List<T>>.indexOfLast(predicate: (T) -> Boolean): Int {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.indexOfLast(predicate)
}

fun <T> LiveData<out List<T>>.count(predicate: (T) -> Boolean): Int {
    requireNotNull(value) { "List in LiveData can't be null" }
    return value!!.count(predicate)
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
