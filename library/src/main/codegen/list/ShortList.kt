@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableShortList.plus(element: Short): ImmutableShortList = newWith(element)
inline operator fun ImmutableShortList.minus(element: Short): ImmutableShortList = newWithout(element)

inline operator fun ImmutableShortList.plus(elements: ShortIterable): ImmutableShortList = newWithAll(elements)
inline operator fun ImmutableShortList.minus(elements: ShortIterable): ImmutableShortList = newWithoutAll(elements)

inline operator fun MutableShortList.plusAssign(element: Short) {
    add(element)
}

inline operator fun MutableShortList.minusAssign(element: Short) {
    remove(element)
}

inline operator fun MutableShortList.plusAssign(element: ShortIterable) {
    addAll(element)
}

inline operator fun MutableShortList.minusAssign(element: ShortIterable) {
    addAll(element)
}
