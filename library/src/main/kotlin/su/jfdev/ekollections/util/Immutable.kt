package su.jfdev.ekollections.util

import org.eclipse.collections.api.list.*
import org.eclipse.collections.api.map.*
import org.eclipse.collections.api.map.sorted.*
import org.eclipse.collections.api.set.*
import org.eclipse.collections.impl.factory.*
import java.util.*

fun <K, V> SortedMap<K, V>.toImmutable(): ImmutableSortedMap<K, V> = SortedMaps.immutable.ofSortedMap(this)
fun <K, V> Map<K, V>.toImmutable(): ImmutableMap<K, V> = Maps.immutable.ofAll(this)
fun <T> List<T>.toImmutable(): ImmutableList<T> = Lists.immutable.ofAll(this)
fun <T> Set<T>.toImmutable(): ImmutableSet<T> = Sets.immutable.ofAll(this)

fun <K, V> SortedMap<K, V>.byImmutable(): SortedMap<K, V> = toImmutable().castToSortedMap()
fun <K, V> Map<K, V>.byImmutable(): Map<K, V> = toImmutable().castToMap()
fun <T> List<T>.byImmutable(): List<T> = toImmutable().castToList()
fun <T> Set<T>.byImmutable(): Set<T> = toImmutable().castToSet()