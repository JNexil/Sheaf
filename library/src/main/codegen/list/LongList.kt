@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableLongList.plus(element: Long): ImmutableLongList = newWith(element)
inline operator fun ImmutableLongList.minus(element: Long): ImmutableLongList = newWithout(element)

inline operator fun ImmutableLongList.plus(elements: LongIterable): ImmutableLongList = newWithAll(elements)
inline operator fun ImmutableLongList.minus(elements: LongIterable): ImmutableLongList = newWithoutAll(elements)

inline operator fun MutableLongList.plusAssign(element: Long) {
    add(element)
}

inline operator fun MutableLongList.minusAssign(element: Long) {
    remove(element)
}

inline operator fun MutableLongList.plusAssign(element: LongIterable) {
    addAll(element)
}

inline operator fun MutableLongList.minusAssign(element: LongIterable) {
    addAll(element)
}
