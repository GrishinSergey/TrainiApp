package com.sagrishin.common.utils


import java.io.Serializable

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


fun <A, B, C> Triple<Iterable<A>, Iterable<B>, Iterable<C>>.iterator() : Iterator<Triple<A,B,C>> {
    val ia = first.iterator()
    val ib = second.iterator()
    val ic = third.iterator()

    return object : Iterator<Triple<A,B,C>> {
        override fun next() : Triple<A, B, C> = Triple(ia.next(), ib.next(), ic.next())
        override fun hasNext() : Boolean = (ia.hasNext() && ib.hasNext() && ic.hasNext())
    }
}

fun <A, B> Pair<Iterable<A>, Iterable<B>>.iterator() : Iterator<Pair<A,B>> {
    val ia = first.iterator()
    val ib = second.iterator()

    return object : Iterator<Pair<A, B>> {
        override fun next() : Pair<A, B> = Pair(ia.next(), ib.next())
        override fun hasNext() : Boolean = (ia.hasNext() && ib.hasNext())
    }
}

fun <E> MutableList<E>.replaceWith(newList: List<E>) {
    this.clear()
    this += newList
}

fun <E> MutableList<E>.replaceBy(new: E, predicate: (E) -> Boolean): MutableList<E> {
    val i = indexOfFirst(predicate)
    if (i >= 0) this[i] = new
    return this
}


fun <E> MutableList<E>.replaceWith(old: E, new: E): MutableList<E> {
    val i = indexOf(old)
    if (i >= 0) this[i] = new
    return this
}


//fun <T> getNonNullOrThrow(t1: T?, t2: T?): T {
//    if (t1 != null) return t1
//    if (t2 != null) return t2
//    throw IllegalStateException("both params are null")
//}

fun <T> getNonNullOrThrow(vararg t: T?): T {
    return requireNotNull(t.find { it != null })
}

fun <T> findNonNull(t1: T?, t2: T?): T? {
    if (t1 != null) return t1
    if (t2 != null) return t2
    return null
}

inline fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    return indexOfFirst(predicate).let { if (it == -1) null else it }
}


sealed class Either<T1, T2> {
    class Left<T1, T2>(val left: T1) : Either<T1, T2>()
    class Right<T1, T2>(val right: T2) : Either<T1, T2>()

    fun leftOrNull(): T1? = if (this is Left<T1, T2>) left else null
    fun rightOrNull(): T2? = if (this is Right<T1, T2>) right else null
}

fun <T1, T2> T1.toLeft(): Either<T1, T2> {
    return Either.Left(this)
}

fun <T1, T2> T2.toRight(): Either<T1, T2> {
    return Either.Right(this)
}


infix fun <T> List<T>.equalTo(list: List<T>): Boolean {
    if (this.size != list.size) return false

    return (this to list).map { t1, t2 -> t1 == t2 }.all { it }
}

data class Quadruple<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {

    override fun toString(): String = "($first, $second, $third, $fourth)"
}

fun <T> Quadruple<T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth)

data class Quintuple<out A, out B, out C, out D, out E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
) : Serializable {

    override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}

fun <T> Quintuple<T, T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth, fifth)


infix fun <A, B, C> Pair<A, B>.to(that: C): Triple<A, B, C> = Triple(first, second, that)


val String.capitalLetters: String
    get() = StringBuilder().apply { this@capitalLetters.split(" ").filter { it.isNotBlank() }.forEach { append(it.first()) } }.toString()


fun <T> List<T>.containsAll(vararg element: T): Boolean {
    return containsAll(element.toList())
}

fun <T> List<T>.findFromLastToFirst(callback: (T) -> Boolean): T? {
    for (i in lastIndex downTo 0) {
        val item = get(i)
        if (callback(item)) return item
    }

    return null
}

fun List<String>.sortByAlphanumericCharacters() {
    this.sortedWith(object : Comparator<String> {
        override fun compare(o1: String, o2: String): Int {
            return extractInt(o1) - extractInt(o2)
        }

        fun extractInt(value: String): Int {
            val num = value.replace("\\D".toRegex(), "")
            return if (num.isEmpty()) 0 else Integer.parseInt(num)
        }

    })


//    this.sortedWith(object : Comparator<String> {
//        override fun compare(o1: String, o2: String): Int {
//            return extractInt(o1) - extractInt(o2)
//        }
//
//        fun extractInt(value: String): Int {
//            val num = value.replace("\\D".toRegex(), "")
//            return if (num.isEmpty()) 0 else Integer.parseInt(num)
//        }
//
//    })
}