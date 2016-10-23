@file:Suppress("PackageDirectoryMismatch", "unused")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIteratorUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.iterator

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.iterator.DoubleIterator
import su.jfdev.ekollections.util.Primitives.NULL_Double
import java.util.*
import kotlin.collections.DoubleIterator as KtIterator

inline fun DoubleIterator.all(crossinline condition: (Double) -> Boolean) = none { !condition(it) }

inline fun DoubleIterator.none(crossinline condition: (Double) -> Boolean): Boolean {
    only(condition) {
        return false
    }
    return true
}

inline fun DoubleIterator.count(crossinline condition: (Double) -> Boolean): Int {
    var counter = 0
    only(condition) {
        counter++
    }
    return counter
}


inline fun DoubleIterator.any(crossinline condition: (Double) -> Boolean): Boolean {
    only(condition) {
        return true
    }
    return false
}

inline fun DoubleIterator.`index of last`(crossinline condition: (Double) -> Boolean): Int {
    var last = -1
    indexedOnly(condition) { i, it ->
        last = i
    }
    return last
}


inline fun DoubleIterator.`index of first`(crossinline condition: (Double) -> Boolean): Int {
    indexedOnly(condition) { i, it ->
        return i
    }
    return -1
}

inline fun DoubleIterator.first(crossinline condition: (Double) -> Boolean) = firstOr(condition) { throw NoSuchElementException() }
inline fun DoubleIterator.firstOr(elseReturn: Double, crossinline condition: (Double) -> Boolean) = firstOr(condition) { elseReturn }
inline fun DoubleIterator.firstOr(crossinline condition: (Double) -> Boolean, elseDo: () -> Double): Double {
    only(condition) {
        return it
    }
    return elseDo()
}

inline fun DoubleIterator.last(crossinline condition: (Double) -> Boolean) = lastOr(condition) { throw NoSuchElementException() }
inline fun DoubleIterator.lastOr(elseReturn: Double, crossinline condition: (Double) -> Boolean) = lastOr(condition) { elseReturn }
inline fun DoubleIterator.lastOr(crossinline condition: (Double) -> Boolean, elseDo: () -> Double): Double {
    var existCandidate: Boolean = false
    var candidate: Double = NULL_Double
    only(condition) {
        existCandidate = true
        candidate = it
    }
    if (existCandidate) return candidate
    else return elseDo()
}


inline fun DoubleIterator.single(crossinline condition: (Double) -> Boolean) = singleOr(condition) { error("Iterator contains 0 or 2+ elements") }
inline fun DoubleIterator.singleOr(elseReturn: Double, crossinline condition: (Double) -> Boolean) = singleOr(condition) { elseReturn }
inline fun DoubleIterator.singleOr(crossinline condition: (Double) -> Boolean, elseDo: () -> Double): Double {
    var existCandidate: Boolean = false
    var candidate: Double = NULL_Double
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

inline fun DoubleIterator.indexedOnly(crossinline condition: (Double) -> Boolean, procedure: (Int, Double) -> Unit) {
    indexedIterate { i, it ->
        if (condition(it)) procedure(i, it)
    }
}

inline fun DoubleIterator.only(crossinline condition: (Double) -> Boolean, procedure: (Double) -> Unit) {
    iterate {
        if (condition(it)) procedure(it)
    }
}

inline infix fun DoubleIterator.indexedIterate(procedure: (Int, Double) -> Unit) {
    var index = 0
    while (hasNext()) procedure(index++, next())
}

inline infix fun DoubleIterator.iterate(procedure: (Double) -> Unit) {
    while (hasNext()) procedure(next())
}

operator fun DoubleIterator.iterator(): KtIterator = object: KtIterator() {
    override fun nextDouble() = this@iterator.next()
    override fun hasNext(): Boolean = this@iterator.hasNext()
}
