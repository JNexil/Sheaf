@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveIterableUtil")
package su.jfdev.ekollections.iterable

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.*
import su.jfdev.ekollections.iterator.*

inline operator fun CharIterable.iterator() = charIterator().iterator()
inline operator fun CharIterable.contains(other: CharIterable): Boolean = containsAll(other)

inline infix fun CharIterable.iterate(procedure: (Char) -> Unit) = charIterator().iterate(procedure)

inline infix fun CharIterable.all(crossinline condition: (Char) -> Boolean) = charIterator().all(condition)
inline infix fun CharIterable.none(crossinline condition: (Char) -> Boolean) = charIterator().none(condition)
inline infix fun CharIterable.count(crossinline condition: (Char) -> Boolean) = charIterator().count(condition)
inline infix fun CharIterable.any(crossinline condition: (Char) -> Boolean) = charIterator().any(condition)

inline infix fun CharIterable.`index of last`(crossinline condition: (Char) -> Boolean)  = charIterator().`index of last`(condition)
inline infix fun CharIterable.`index of first`(crossinline condition: (Char) -> Boolean)  = charIterator().`index of first`(condition)

inline infix fun CharIterable.first(crossinline condition: (Char) -> Boolean)  = charIterator().first(condition)
inline fun CharIterable.firstOr(elseReturn: Char, crossinline condition: (Char) -> Boolean) = charIterator().firstOr(elseReturn, condition)

inline infix fun CharIterable.last(crossinline condition: (Char) -> Boolean)  = charIterator().last(condition)
inline fun CharIterable.lastOr(elseReturn: Char, crossinline condition: (Char) -> Boolean) = charIterator().lastOr(elseReturn, condition)

inline infix fun CharIterable.single(crossinline condition: (Char) -> Boolean)  = charIterator().single(condition)
inline fun CharIterable.singleOr(elseReturn: Char, crossinline condition: (Char) -> Boolean) = charIterator().singleOr(elseReturn, condition)
