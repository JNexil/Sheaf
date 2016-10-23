@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableIntList.plus(element: Int): ImmutableIntList = newWith(element)
inline operator fun ImmutableIntList.minus(element: Int): ImmutableIntList = newWithout(element)

inline operator fun ImmutableIntList.plus(elements: IntIterable): ImmutableIntList = newWithAll(elements)
inline operator fun ImmutableIntList.minus(elements: IntIterable): ImmutableIntList = newWithoutAll(elements)

inline operator fun MutableIntList.plusAssign(element: Int) {
    add(element)
}

inline operator fun MutableIntList.minusAssign(element: Int) {
    remove(element)
}

inline operator fun MutableIntList.plusAssign(element: IntIterable) {
    addAll(element)
}

inline operator fun MutableIntList.minusAssign(element: IntIterable) {
    addAll(element)
}
