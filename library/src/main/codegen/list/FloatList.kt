@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableFloatList.plus(element: Float): ImmutableFloatList = newWith(element)
inline operator fun ImmutableFloatList.minus(element: Float): ImmutableFloatList = newWithout(element)

inline operator fun ImmutableFloatList.plus(elements: FloatIterable): ImmutableFloatList = newWithAll(elements)
inline operator fun ImmutableFloatList.minus(elements: FloatIterable): ImmutableFloatList = newWithoutAll(elements)

inline operator fun MutableFloatList.plusAssign(element: Float) {
    add(element)
}

inline operator fun MutableFloatList.minusAssign(element: Float) {
    remove(element)
}

inline operator fun MutableFloatList.plusAssign(element: FloatIterable) {
    addAll(element)
}

inline operator fun MutableFloatList.minusAssign(element: FloatIterable) {
    addAll(element)
}
