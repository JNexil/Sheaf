package su.jfdev.sheaf.registry

import org.eclipse.collections.api.bimap.*
import org.eclipse.collections.impl.factory.*
import su.jfdev.anci.util.syntax.*
import su.jfdev.ekollections.readonly.*
import su.jfdev.ekollections.util.*
import su.jfdev.sheaf.registrar.*
import java.util.*
import java.util.concurrent.*

object Registries {
    infix fun <T: Identified> copy(map: Map<UUID, T>) = about(map = map.byImmutable())
    infix fun <T: Identified> copy(registry: Registry<T>) = about(registry.toMap())

    infix fun <T: Identified> about(map: Map<UUID, T>) = object: Registry<T> {
        override fun iterator(): Iterator<T> = map.values.iterator().readOnly()
        override val size: Int = map.size
        override fun contains(uuid: UUID) = map.containsKey(uuid)
        override fun contains(value: T) = map.containsValue(value)
        override fun get(uuid: UUID): T? = map[uuid]

        override fun toString(): String = "Registry: $map"

        override fun equals(other: Any?): Boolean = other is Registry<*> && other `have same content` map

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    infix fun <S: Any, R: Identified> registrar(transformer: (S) -> R?) = object: Registrar<S, R> {
        private val _registry: ConcurrentMap<UUID, R> = ConcurrentHashMap()
        private val _sources: MutableBiMap<S, R> = BiMaps.mutable.empty<S, R>().asSynchronized()

        override val registry: Registry<R> = about(_registry)
        override val sources: Map<S, R> = _sources.readOnlyMap()

        override fun register(source: S): R? = when (source) {
            in _sources.keys -> throw AlreadyRegisteredException()
            else             -> create(source)
        }

        private fun create(source: S) = transformer(source) `when not null` {
            val previous = _sources.getIfAbsentPut(source, it)
            when {
                previous === it -> _registry.put(it.uuid, it)
                else            -> throw AlreadyRegisteredException()
            }
        }

        override fun unregister(source: S): R? = _sources.remove(source) `when not null` {
            _registry.remove(it.uuid)
        }

        override fun unregister(registration: R): S? = _sources.inverse().remove(registration) `when not null` {
            _registry.remove(registration.uuid)
        }
    }
}