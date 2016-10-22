package su.jfdev.ekollections.readonly

import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.junit.platform.runner.*
import org.junit.runner.*
import su.jfdev.test.immutability.util.*
import java.util.*

@RunWith(JUnitPlatform::class)
class ROSetTest: Spek(fun Dsl.() {
    Immutabilities.set(randomUUID)(specAdapter) {
        ROSet(delegate = uuidSet)
    }
})

@RunWith(JUnitPlatform::class)
class ROSequenceTest: Spek(fun Dsl.() {
    Immutabilities.iterator(specAdapter) {
        ROSequence(delegate = uuidSequence).iterator()
    }
})


@RunWith(JUnitPlatform::class)
class ROListTest: Spek(fun Dsl.() {
    Immutabilities.list(randomUUID)(specAdapter) {
        ROList(delegate = LinkedList(uuidList))
    }
})


@RunWith(JUnitPlatform::class)
class RORandomListTest: Spek(fun Dsl.() {
    Immutabilities.list(randomUUID)(specAdapter) {
        ROList(delegate = ArrayList(uuidList))
    }
})

@RunWith(JUnitPlatform::class)
class ROCollectionTest: Spek(fun Dsl.() {
    Immutabilities.collection(randomUUID)(specAdapter) {
        ROCollection(delegate = uuidList)
    }
})

@RunWith(JUnitPlatform::class)
class ROIterableTest: Spek(fun Dsl.() {
    Immutabilities.iterable(specAdapter) {
        ROIterable(delegate = uuidList)
    }
})

@RunWith(JUnitPlatform::class)
class ROIteratorTest: Spek(fun Dsl.() {
    Immutabilities.iterator(specAdapter) {
        ROIterator(delegate = uuidIterator)
    }
})

@RunWith(JUnitPlatform::class)
class ROListIteratorTest: Spek(fun Dsl.() {
    Immutabilities.listIterator(randomUUID)(specAdapter) {
        ROListIterator(delegate = uuidListIterator)
    }
})
