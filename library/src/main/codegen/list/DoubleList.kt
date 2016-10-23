@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableDoubleList.plus(element: Double): ImmutableDoubleList = newWith(element)
inline operator fun ImmutableDoubleList.minus(element: Double): ImmutableDoubleList = newWithout(element)

inline operator fun ImmutableDoubleList.plus(elements: DoubleIterable): ImmutableDoubleList = newWithAll(elements)
inline operator fun ImmutableDoubleList.minus(elements: DoubleIterable): ImmutableDoubleList = newWithoutAll(elements)

inline operator fun MutableDoubleList.plusAssign(element: Double) {
    add(element)
}

inline operator fun MutableDoubleList.minusAssign(element: Double) {
    remove(element)
}

inline operator fun MutableDoubleList.plusAssign(element: DoubleIterable) {
    addAll(element)
}

inline operator fun MutableDoubleList.minusAssign(element: DoubleIterable) {
    addAll(element)
}
