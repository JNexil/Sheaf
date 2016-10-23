@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIterableUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.iterable

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.*
import su.jfdev.ekollections.iterator.*

inline operator fun LongIterable.iterator() = longIterator().iterator()
inline operator fun LongIterable.contains(other: Long): Boolean = contains(other)
inline operator fun LongIterable.contains(other: LongIterable): Boolean = containsAll(other)

inline infix fun LongIterable.iterate(procedure: (Long) -> Unit) = longIterator().iterate(procedure)

inline infix fun LongIterable.all(crossinline condition: (Long) -> Boolean) = longIterator().all(condition)
inline infix fun LongIterable.none(crossinline condition: (Long) -> Boolean) = longIterator().none(condition)
inline infix fun LongIterable.count(crossinline condition: (Long) -> Boolean) = longIterator().count(condition)
inline infix fun LongIterable.any(crossinline condition: (Long) -> Boolean) = longIterator().any(condition)

inline infix fun LongIterable.`index of last`(crossinline condition: (Long) -> Boolean)  = longIterator().`index of last`(condition)
inline infix fun LongIterable.`index of first`(crossinline condition: (Long) -> Boolean)  = longIterator().`index of first`(condition)

inline infix fun LongIterable.first(crossinline condition: (Long) -> Boolean)  = longIterator().first(condition)
inline fun LongIterable.firstOr(elseReturn: Long, crossinline condition: (Long) -> Boolean) = longIterator().firstOr(elseReturn, condition)

inline infix fun LongIterable.last(crossinline condition: (Long) -> Boolean)  = longIterator().last(condition)
inline fun LongIterable.lastOr(elseReturn: Long, crossinline condition: (Long) -> Boolean) = longIterator().lastOr(elseReturn, condition)

inline infix fun LongIterable.single(crossinline condition: (Long) -> Boolean)  = longIterator().single(condition)
inline fun LongIterable.singleOr(elseReturn: Long, crossinline condition: (Long) -> Boolean) = longIterator().singleOr(elseReturn, condition)
