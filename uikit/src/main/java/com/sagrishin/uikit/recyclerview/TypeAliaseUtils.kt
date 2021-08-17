package com.sagrishin.uikit.recyclerview

import android.view.ViewGroup

typealias HolderPredicate<T> = (item: T) -> Boolean
typealias HolderGenerator<T> = (ViewGroup) -> BaseHolder<out T>
typealias Initializer<T> = (MultiTypeAdapter<T>) -> Unit
