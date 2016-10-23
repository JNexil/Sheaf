@file:Suppress("PackageDirectoryMismatch", "unused")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIteratorUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.iterator

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.iterator.LongIterator
import su.jfdev.ekollections.util.Primitives.NULL_Long
import java.util.*
import kotlin.collections.LongIterator as KtIterator

inline fun LongIterator.all(crossinline condition: (Long) -> Boolean) = none { !condition(it) }

inline fun LongIterator.none(crossinline condition: (Long) -> Boolean): Boolean {
    only(condition) {
        return false
    }
    return true
}

inline fun LongIterator.count(crossinline condition: (Long) -> Boolean): Int {
    var counter = 0
    only(condition) {
        counter++
    }
    return counter
}


inline fun LongIterator.any(crossinline condition: (Long) -> Boolean): Boolean {
    only(condition) {
        return true
    }
    return false
}

inline fun LongIterator.`index of last`(crossinline condition: (Long) -> Boolean): Int {
    var last = -1
    indexedOnly(condition) { i, it ->
        last = i
    }
    return last
}


inline fun LongIterator.`index of first`(crossinline condition: (Long) -> Boolean): Int {
    indexedOnly(condition) { i, it ->
        return i
    }
    return -1
}

inline fun LongIterator.first(crossinline condition: (Long) -> Boolean) = firstOr(condition) { throw NoSuchElementException() }
inline fun LongIterator.firstOr(elseReturn: Long, crossinline condition: (Long) -> Boolean) = firstOr(condition) { elseReturn }
inline fun LongIterator.firstOr(crossinline condition: (Long) -> Boolean, elseDo: () -> Long): Long {
    only(condition) {
        return it
    }
    return elseDo()
}

inline fun LongIterator.last(crossinline condition: (Long) -> Boolean) = lastOr(condition) { throw NoSuchElementException() }
inline fun LongIterator.lastOr(elseReturn: Long, crossinline condition: (Long) -> Boolean) = lastOr(condition) { elseReturn }
inline fun LongIterator.lastOr(crossinline condition: (Long) -> Boolean, elseDo: () -> Long): Long {
    var existCandidate: Boolean = false
    var candidate: Long = NULL_Long
    only(condition) {
        existCandidate = true
        candidate = it
    }
    if (existCandidate) return candidate
    else return elseDo()
}


inline fun LongIterator.single(crossinline condition: (Long) -> Boolean) = singleOr(condition) { error("Iterator contains 0 or 2+ elements") }
inline fun LongIterator.singleOr(elseReturn: Long, crossinline condition: (Long) -> Boolean) = singleOr(condition) { elseReturn }
inline fun LongIterator.singleOr(crossinline condition: (Long) -> Boolean, elseDo: () -> Long): Long {
    var existCandidate: Boolean = false
    var candidate: Long = NULL_Long
    only(condition) {
        if (existCandidate) return elseDo()
        else {
            existCandidate = true
            candidate = it
        }
    }
    if (existCandidate) return candidate
    else return elseDo()
}

inline fun LongIterator.indexedOnly(crossinline condition: (Long) -> Boolean, procedure: (Int, Long) -> Unit) {
    indexedIterate { i, it ->
        if (condition(it)) procedure(i, it)
    }
}

inline fun LongIterator.only(crossinline condition: (Long) -> Boolean, procedure: (Long) -> Unit) {
    iterate {
        if (condition(it)) procedure(it)
    }
}

inline infix fun LongIterator.indexedIterate(procedure: (Int, Long) -> Unit) {
    var index = 0
    while (hasNext()) procedure(index++, next())
}

inline infix fun LongIterator.iterate(procedure: (Long) -> Unit) {
    while (hasNext()) procedure(next())
}

operator fun LongIterator.iterator(): KtIterator = object: KtIterator() {
    override fun nextLong() = this@iterator.next()
    override fun hasNext(): Boolean = this@iterator.hasNext()
}
