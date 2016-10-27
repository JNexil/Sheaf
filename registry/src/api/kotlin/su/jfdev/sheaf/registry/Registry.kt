package su.jfdev.sheaf.registry

import java.util.*

interface Registry<V: Identified>: Iterable<V> {
    val size: Int
    operator fun get(uuid: UUID): V?
    infix operator fun contains(uuid: UUID): Boolean
    infix operator fun contains(value: V): Boolean
    fun isEmpty(): Boolean = size == 0
}