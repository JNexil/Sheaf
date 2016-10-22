package su.jfdev.ekollections.readonly

import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.junit.platform.runner.*
import org.junit.runner.*
import su.jfdev.test.immutability.util.*
import java.util.*

@RunWith(JUnitPlatform::class)
class ROMapTest: Spek(fun Dsl.() {
    MockImmutabilities.uuidMap<Any>()(specAdapter) {
        ROMap(delegate = uuidMap)
    }
})

@RunWith(JUnitPlatform::class)
class ROEntryTest: Spek(fun Dsl.() {
    Immutabilities.entry<UUID, Any>(randomUUID)(specAdapter) {
        ROEntry(delegate = uuidEntry)
    }
})