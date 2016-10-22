package su.jfdev.ekollections.readonly

import kotlin.collections.Map.*

class ROEntriesIterator<out K, out V>(override val delegate: Iterator<Entry<K, V>>): ReadOnly(), Iterator<Entry<K, V>> by delegate {
    override fun next(): Entry<K, V> = ROEntry(delegate.next())
}