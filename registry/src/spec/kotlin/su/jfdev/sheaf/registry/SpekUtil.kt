package su.jfdev.sheaf.registry

import org.jetbrains.spek.api.dsl.*
import java.util.concurrent.atomic.*
import kotlin.test.*

fun Dsl.should(description: String, body: () -> Boolean) {
    val desc = "should $description"
    test(desc) {
        assertTrue(message = desc, block = body)
    }
}

fun Dsl.shouldNot(description: String, body: () -> Boolean) = should("not $description") {
    !body()
}

fun Dsl.before(callback: () -> Unit) {
    val need = AtomicBoolean(true)
    beforeEach {
        if (need.compareAndSet(true, false)) callback()
    }
}

fun <R> Dsl.receiving(callback: () -> R) = AtomicReference<R>().apply {
    before {
        set(callback())
    }
}