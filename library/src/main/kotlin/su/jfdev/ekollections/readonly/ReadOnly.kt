package su.jfdev.ekollections.readonly

import su.jfdev.ekollections.readonly.ROEntriesSet.*
import java.io.*
import java.util.*
import kotlin.collections.Map.*

abstract class ReadOnly: Serializable {
    protected abstract val delegate: Any
    override fun equals(other: Any?) = delegate == other
    override fun hashCode(): Int = delegate.hashCode()
    override fun toString(): String = "ReadOnly: $delegate"

    private companion object {
        @JvmField val serialVersionUID = 1L
    }
}

fun <K, V> Map<K, V>.readOnly() = readOnly { ROMap(it) }
fun <K, V> Entry<K, V>.readOnly() = readOnly { ROEntry(it) }
fun <K, V> Iterator<Entry<K,V>>.readOnlyEntries() = readOnly { ROEntriesIterator(it) }
fun <K, V> Set<Entry<K,V>>.readOnlyEntries() = readOnly { ROEntriesSet(it) }
fun <T> Iterator<T>.readOnly() = readOnly { ROIterator(it) }
fun <T> ListIterator<T>.readOnly() = readOnly { ROListIterator(it) }
fun <T> Iterable<T>.readOnly() = readOnly { ROIterable(it) }
fun <T> Collection<T>.readOnly() = readOnly { ROCollection(it) }
fun <T> Set<T>.readOnly() = readOnly { ROSet(it) }
fun <T> List<T>.readOnly() = readOnlyList()

fun <K, V> Map<K, V>.readOnlyMap() = readOnly { ROMap(it) }
fun <K, V> Entry<K, V>.readOnlyEntry() = readOnly { ROEntry(it) }
fun <K, V> Iterator<Entry<K,V>>.readOnlyEntriesIterator() = readOnly { ROEntriesIterator(it) }
fun <K, V> Set<Entry<K,V>>.readOnlyEntriesSet() = readOnly { ROEntriesSet(it) }
fun <T> Iterator<T>.readOnlyIterable() = readOnly { ROIterator(it) }
fun <T> ListIterator<T>.readOnlyListIterator() = readOnly { ROListIterator(it) }
fun <T> Iterable<T>.readOnlyIterable() = readOnly { ROIterable(it) }
fun <T> Collection<T>.readOnlyCollection() = readOnly { ROCollection(it) }
fun <T> Set<T>.readOnlySet() = readOnly { ROSet(it) }
fun <T> List<T>.readOnlyList() = when (this) {
    is RORandomList, is ROList -> this
    is RandomAccess            -> RORandomList(this)
    else                       -> ROList(this)
}

private inline fun <F, reified RO: ReadOnly> F.readOnly(factory: (F) -> RO): RO = when (this) {
    is RO -> this
    else  -> factory(this)
}