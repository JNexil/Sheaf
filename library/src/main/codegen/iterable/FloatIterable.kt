@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIterableUtil")
package su.jfdev.ekollections.iterable

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.*
import su.jfdev.ekollections.iterator.*

inline operator fun FloatIterable.iterator() = floatIterator().iterator()
inline operator fun FloatIterable.contains(other: FloatIterable): Boolean = containsAll(other)

inline infix fun FloatIterable.iterate(procedure: (Float) -> Unit) = floatIterator().iterate(procedure)

inline infix fun FloatIterable.all(crossinline condition: (Float) -> Boolean) = floatIterator().all(condition)
inline infix fun FloatIterable.none(crossinline condition: (Float) -> Boolean) = floatIterator().none(condition)
inline infix fun FloatIterable.count(crossinline condition: (Float) -> Boolean) = floatIterator().count(condition)
inline infix fun FloatIterable.any(crossinline condition: (Float) -> Boolean) = floatIterator().any(condition)

inline infix fun FloatIterable.`index of last`(crossinline condition: (Float) -> Boolean)  = floatIterator().`index of last`(condition)
inline infix fun FloatIterable.`index of first`(crossinline condition: (Float) -> Boolean)  = floatIterator().`index of first`(condition)

inline infix fun FloatIterable.first(crossinline condition: (Float) -> Boolean)  = floatIterator().first(condition)
inline fun FloatIterable.firstOr(elseReturn: Float, crossinline condition: (Float) -> Boolean) = floatIterator().firstOr(elseReturn, condition)

inline infix fun FloatIterable.last(crossinline condition: (Float) -> Boolean)  = floatIterator().last(condition)
inline fun FloatIterable.lastOr(elseReturn: Float, crossinline condition: (Float) -> Boolean) = floatIterator().lastOr(elseReturn, condition)

inline infix fun FloatIterable.single(crossinline condition: (Float) -> Boolean)  = floatIterator().single(condition)
inline fun FloatIterable.singleOr(elseReturn: Float, crossinline condition: (Float) -> Boolean) = floatIterator().singleOr(elseReturn, condition)
