@file:Suppress("PackageDirectoryMismatch", "unused")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIteratorUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.iterator

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.iterator.BooleanIterator
import su.jfdev.ekollections.util.Primitives.NULL_Boolean
import java.util.*
import kotlin.collections.BooleanIterator as KtIterator

inline fun BooleanIterator.all(crossinline condition: (Boolean) -> Boolean) = none { !condition(it) }

inline fun BooleanIterator.none(crossinline condition: (Boolean) -> Boolean): Boolean {
    only(condition) {
        return false
    }
    return true
}

inline fun BooleanIterator.count(crossinline condition: (Boolean) -> Boolean): Int {
    var counter = 0
    only(condition) {
        counter++
    }
    return counter
}


inline fun BooleanIterator.any(crossinline condition: (Boolean) -> Boolean): Boolean {
    only(condition) {
        return true
    }
    return false
}

inline fun BooleanIterator.`index of last`(crossinline condition: (Boolean) -> Boolean): Int {
    var last = -1
    indexedOnly(condition) { i, it ->
        last = i
    }
    return last
}


inline fun BooleanIterator.`index of first`(crossinline condition: (Boolean) -> Boolean): Int {
    indexedOnly(condition) { i, it ->
        return i
    }
    return -1
}

inline fun BooleanIterator.first(crossinline condition: (Boolean) -> Boolean) = firstOr(condition) { throw NoSuchElementException() }
inline fun BooleanIterator.firstOr(elseReturn: Boolean, crossinline condition: (Boolean) -> Boolean) = firstOr(condition) { elseReturn }
inline fun BooleanIterator.firstOr(crossinline condition: (Boolean) -> Boolean, elseDo: () -> Boolean): Boolean {
    only(condition) {
        return it
    }
    return elseDo()
}

inline fun BooleanIterator.last(crossinline condition: (Boolean) -> Boolean) = lastOr(condition) { throw NoSuchElementException() }
inline fun BooleanIterator.lastOr(elseReturn: Boolean, crossinline condition: (Boolean) -> Boolean) = lastOr(condition) { elseReturn }
inline fun BooleanIterator.lastOr(crossinline condition: (Boolean) -> Boolean, elseDo: () -> Boolean): Boolean {
    var existCandidate: Boolean = false
    var candidate: Boolean = NULL_Boolean
    only(condition) {
        existCandidate = true
        candidate = it
    }
    if (existCandidate) return candidate
    else return elseDo()
}


inline fun BooleanIterator.single(crossinline condition: (Boolean) -> Boolean) = singleOr(condition) { error("Iterator contains 0 or 2+ elements") }
inline fun BooleanIterator.singleOr(elseReturn: Boolean, crossinline condition: (Boolean) -> Boolean) = singleOr(condition) { elseReturn }
inline fun BooleanIterator.singleOr(crossinline condition: (Boolean) -> Boolean, elseDo: () -> Boolean): Boolean {
    var existCandidate: Boolean = false
    var candidate: Boolean = NULL_Boolean
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

inline fun BooleanIterator.indexedOnly(crossinline condition: (Boolean) -> Boolean, procedure: (Int, Boolean) -> Unit) {
    indexedIterate { i, it ->
        if (condition(it)) procedure(i, it)
    }
}

inline fun BooleanIterator.only(crossinline condition: (Boolean) -> Boolean, procedure: (Boolean) -> Unit) {
    iterate {
        if (condition(it)) procedure(it)
    }
}

inline infix fun BooleanIterator.indexedIterate(procedure: (Int, Boolean) -> Unit) {
    var index = 0
    while (hasNext()) procedure(index++, next())
}

inline infix fun BooleanIterator.iterate(procedure: (Boolean) -> Unit) {
    while (hasNext()) procedure(next())
}

operator fun BooleanIterator.iterator(): KtIterator = object: KtIterator() {
    override fun nextBoolean() = this@iterator.next()
    override fun hasNext(): Boolean = this@iterator.hasNext()
}
