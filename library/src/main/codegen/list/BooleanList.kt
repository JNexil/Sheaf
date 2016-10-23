@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableBooleanList.plus(element: Boolean): ImmutableBooleanList = newWith(element)
inline operator fun ImmutableBooleanList.minus(element: Boolean): ImmutableBooleanList = newWithout(element)

inline operator fun ImmutableBooleanList.plus(elements: BooleanIterable): ImmutableBooleanList = newWithAll(elements)
inline operator fun ImmutableBooleanList.minus(elements: BooleanIterable): ImmutableBooleanList = newWithoutAll(elements)

inline operator fun MutableBooleanList.plusAssign(element: Boolean) {
    add(element)
}

inline operator fun MutableBooleanList.minusAssign(element: Boolean) {
    remove(element)
}

inline operator fun MutableBooleanList.plusAssign(element: BooleanIterable) {
    addAll(element)
}

inline operator fun MutableBooleanList.minusAssign(element: BooleanIterable) {
    addAll(element)
}
