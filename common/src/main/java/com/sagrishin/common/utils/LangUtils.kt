package com.sagrishin.common.utils

fun <T, K, V> List<T>.toMap(mapper: (T) -> Pair<K, V>): Map<K, V> {
    return map(mapper).toMap()
}


inline fun <T1, T2, R> Pair<List<T1>, List<T2>>.map(crossinline l: (T1, T2) -> R): List<R> {
    val res = mutableListOf<R>()
    iterator().forEach { res += l(it.first, it.second) }
    return res
}


inline fun <T1, T2, R> Pair<List<T1>, List<T2>>.forEach(crossinline l: (T1, T2) -> R) {
    iterator().forEach { l(it.first, it.second) }
}


fun <A, B, C> Triple<Iterable<A>, Iterable<B>, Iterable<C>>.iterator(): Iterator<Triple<A, B, C>> {
    val ia = first.iterator()
    val ib = second.iterator()
    val ic = third.iterator()

    return object : Iterator<Triple<A, B, C>> {
        override fun next(): Triple<A, B, C> = Triple(ia.next(), ib.next(), ic.next())
        override fun hasNext(): Boolean = (ia.hasNext() && ib.hasNext() && ic.hasNext())
    }
}


fun <A, B> Pair<Iterable<A>, Iterable<B>>.iterator(): Iterator<Pair<A, B>> {
    val ia = first.iterator()
    val ib = second.iterator()

    return object : Iterator<Pair<A, B>> {
        override fun next(): Pair<A, B> = Pair(ia.next(), ib.next())
        override fun hasNext(): Boolean = (ia.hasNext() && ib.hasNext())
    }
}
