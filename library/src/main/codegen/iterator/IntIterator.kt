@file:Suppress("PackageDirectoryMismatch", "unused")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIteratorUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.iterator

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.iterator.IntIterator
import su.jfdev.ekollections.util.Primitives.NULL_Int
import java.util.*
import kotlin.collections.IntIterator as KtIterator

inline fun IntIterator.all(crossinline condition: (Int) -> Boolean) = none { !condition(it) }

inline fun IntIterator.none(crossinline condition: (Int) -> Boolean): Boolean {
    only(condition) {
        return false
    }
    return true
}

inline fun IntIterator.count(crossinline condition: (Int) -> Boolean): Int {
    var counter = 0
    only(condition) {
        counter++
    }
    return counter
}


inline fun IntIterator.any(crossinline condition: (Int) -> Boolean): Boolean {
    only(condition) {
        return true
    }
    return false
}

inline fun IntIterator.`index of last`(crossinline condition: (Int) -> Boolean): Int {
    var last = -1
    indexedOnly(condition) { i, it ->
        last = i
    }
    return last
}


inline fun IntIterator.`index of first`(crossinline condition: (Int) -> Boolean): Int {
    indexedOnly(condition) { i, it ->
        return i
    }
    return -1
}

inline fun IntIterator.first(crossinline condition: (Int) -> Boolean) = firstOr(condition) { throw NoSuchElementException() }
inline fun IntIterator.firstOr(elseReturn: Int, crossinline condition: (Int) -> Boolean) = firstOr(condition) { elseReturn }
inline fun IntIterator.firstOr(crossinline condition: (Int) -> Boolean, elseDo: () -> Int): Int {
    only(condition) {
        return it
    }
    return elseDo()
}

inline fun IntIterator.last(crossinline condition: (Int) -> Boolean) = lastOr(condition) { throw NoSuchElementException() }
inline fun IntIterator.lastOr(elseReturn: Int, crossinline condition: (Int) -> Boolean) = lastOr(condition) { elseReturn }
inline fun IntIterator.lastOr(crossinline condition: (Int) -> Boolean, elseDo: () -> Int): Int {
    var existCandidate: Boolean = false
    var candidate: Int = NULL_Int
    only(condition) {
        existCandidate = true
        candidate = it
    }
    if (existCandidate) return candidate
    else return elseDo()
}


inline fun IntIterator.single(crossinline condition: (Int) -> Boolean) = singleOr(condition) { error("Iterator contains 0 or 2+ elements") }
inline fun IntIterator.singleOr(elseReturn: Int, crossinline condition: (Int) -> Boolean) = singleOr(condition) { elseReturn }
inline fun IntIterator.singleOr(crossinline condition: (Int) -> Boolean, elseDo: () -> Int): Int {
    var existCandidate: Boolean = false
    var candidate: Int = NULL_Int
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

inline fun IntIterator.indexedOnly(crossinline condition: (Int) -> Boolean, procedure: (Int, Int) -> Unit) {
    indexedIterate { i, it ->
        if (condition(it)) procedure(i, it)
    }
}

inline fun IntIterator.only(crossinline condition: (Int) -> Boolean, procedure: (Int) -> Unit) {
    iterate {
        if (condition(it)) procedure(it)
    }
}

inline infix fun IntIterator.indexedIterate(procedure: (Int, Int) -> Unit) {
    var index = 0
    while (hasNext()) procedure(index++, next())
}

inline infix fun IntIterator.iterate(procedure: (Int) -> Unit) {
    while (hasNext()) procedure(next())
}

operator fun IntIterator.iterator(): KtIterator = object: KtIterator() {
    override fun nextInt() = this@iterator.next()
    override fun hasNext(): Boolean = this@iterator.hasNext()
}
