@file:Suppress("PackageDirectoryMismatch", "unused", "NOTHING_TO_INLINE")
@file:JvmMultifileClass
@file:JvmName("PrimitiveListUtil")

//define temp=su.jfdev.ekollections
package su.jfdev.ekollections.list

import org.eclipse.collections.api.*
import org.eclipse.collections.api.list.primitive.*

//define Short=#PRIM#
//define short=#prim#

inline operator fun ImmutableByteList.plus(element: Byte): ImmutableByteList = newWith(element)
inline operator fun ImmutableByteList.minus(element: Byte): ImmutableByteList = newWithout(element)

inline operator fun ImmutableByteList.plus(elements: ByteIterable): ImmutableByteList = newWithAll(elements)
inline operator fun ImmutableByteList.minus(elements: ByteIterable): ImmutableByteList = newWithoutAll(elements)

inline operator fun MutableByteList.plusAssign(element: Byte) {
    add(element)
}

inline operator fun MutableByteList.minusAssign(element: Byte) {
    remove(element)
}

inline operator fun MutableByteList.plusAssign(element: ByteIterable) {
    addAll(element)
}

inline operator fun MutableByteList.minusAssign(element: ByteIterable) {
    addAll(element)
}
