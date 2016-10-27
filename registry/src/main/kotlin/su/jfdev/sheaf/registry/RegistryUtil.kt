package su.jfdev.sheaf.registry

import su.jfdev.anci.util.syntax.*
import java.util.*

fun <T: Identified> Registry<T>.toMap() = associateBy(keySelector = Identified::uuid)

operator fun <T: Identified> MutableMap<UUID, in T>.plusAssign(identified: T) {
    put(identified.uuid, identified)
}

operator fun <T: Identified> Map<UUID, T>.plus(identified: T): Map<UUID, T> = when {
    isEmpty() -> Collections.singletonMap(identified.uuid, identified)
    else      -> LinkedHashMap(this) finally {
        it[identified.uuid] = identified
    }
}

operator fun Registry<*>.contains(map: Map<UUID, *>): Boolean = map.all {
    val (key, value) = it
    value is Identified && value.uuid == key && contains(value)
}

infix fun Registry<*>.`have same content`(map: Map<UUID, *>): Boolean = size == map.size && contains(map)

operator fun Registry<*>.contains(registry: Registry<*>) = registry === registry || registry.all {
    contains(it)
}


infix fun Registry<*>.`have same content`(registry: Registry<*>): Boolean = registry === registry || size == registry.size && contains(registry)