package su.jfdev.ekollections.readonly

import org.jetbrains.spek.api.dsl.*
import su.jfdev.test.immutability.*
import java.util.*
import java.util.AbstractMap.*

val Dsl.specAdapter: SpecAdapter get() = object: SpecAdapter {
    var dsl: Dsl = this@specAdapter
    override fun case(name: String, block: () -> Unit) = dsl.test(name, body = block)
    override fun suite(name: String, block: () -> Unit) = dsl.group(name) {
        val prev = dsl
        dsl = this
        block()
        dsl = prev
    }
}

internal val randomUUID = UUID::randomUUID
internal val uuidSet: Set<UUID> get() = uuidList.toSet()
internal val uuidSequence: Sequence<UUID> get() = uuidList.asSequence()
internal val uuidListIterator: ListIterator<UUID> get() = uuidList.listIterator()
internal val uuidIterator: Iterator<UUID> get() = uuidList.iterator()
internal val uuidList: List<UUID> get() = generateSequence(randomUUID).take(200).toList()

internal val uuidEntry: Map.Entry<UUID, Any> get() = SimpleEntry(UUID.randomUUID(), UUID.randomUUID())
internal val uuidMap: Map<UUID, Any> get() = generateSequence(::uuidEntry).take(200).associate {
    it.key to it.value
}
