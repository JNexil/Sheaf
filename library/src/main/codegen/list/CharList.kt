@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableCharList.plus(element: Char): ImmutableCharList = newWith(element)
inline operator fun ImmutableCharList.minus(element: Char): ImmutableCharList = newWithout(element)

inline operator fun ImmutableCharList.plus(elements: CharIterable): ImmutableCharList = newWithAll(elements)
inline operator fun ImmutableCharList.minus(elements: CharIterable): ImmutableCharList = newWithoutAll(elements)

inline operator fun MutableCharList.plusAssign(element: Char) {
    add(element)
}

inline operator fun MutableCharList.minusAssign(element: Char) {
    remove(element)
}

inline operator fun MutableCharList.plusAssign(element: CharIterable) {
    addAll(element)
}

inline operator fun MutableCharList.minusAssign(element: CharIterable) {
    addAll(element)
}
