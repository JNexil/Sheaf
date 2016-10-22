package su.jfdev.ekollections.readonly

class ROEntry<out K, out V>(override val delegate: Map.Entry<K,V>): ReadOnly(), Map.Entry<K,V> by delegate