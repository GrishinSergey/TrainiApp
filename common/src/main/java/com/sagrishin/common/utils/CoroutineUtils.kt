package com.sagrishin.common.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip

fun <T> List<Flow<T>>.zipFlows(): Flow<List<T>> = when (this.size) {
    0 -> flowOf(listOf())
    1 -> this[0].map { listOf(it) }
    2 -> this[0].zip(this[1]) { a, b -> listOf(a, b) }
    else -> {
        var accFlow = this[0].zip(this[1]) { a, b -> listOf(a, b) }
        for (i in 2 until this.size) {
            accFlow = accFlow.zip(this[i]) { list, it -> list + it }
        }
        accFlow
    }
}
