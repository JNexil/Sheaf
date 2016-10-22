package su.jfdev.ekollections.readonly

import kotlin.collections.Map.*

/**
 * @param deepContains - use [contains] secondly with [ROEntry]
 */
class ROEntriesSet<K, V>(override val delegate: Set<Entry<K, V>>, private val deepContains: Boolean): ReadOnly(), Set<Entry<K, V>> by delegate {
    constructor(delegate: Set<Entry<K, V>>): this(delegate, deepContains = true)
    override fun contains(element: Entry<K, V>): Boolean = element in delegate || deepContains && ROEntry(element) in delegate
    override fun containsAll(elements: Collection<Entry<K, V>>): Boolean = elements.all { contains(it) }

    override fun iterator() = ROEntriesIterator(delegate.iterator())
}

