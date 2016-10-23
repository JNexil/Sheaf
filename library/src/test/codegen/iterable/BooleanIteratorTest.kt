//define temp=su.jfdev.ekollections
@file:Suppress("PackageDirectoryMismatch")

package su.jfdev.ekollections.iterator

//define Short=#PRIM#
//define short=#prim#

import org.eclipse.collections.api.iterator.BooleanIterator
import org.eclipse.collections.impl.factory.primitive.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.junit.platform.runner.*
import org.junit.runner.*
import su.jfdev.ekollections.util.*
import su.jfdev.ekollections.util.Primitives.randomBoolean
import su.jfdev.test.fluent.*
import java.util.*
import java.util.concurrent.*
import su.jfdev.ekollections.util.Primitives.randomBoolean as randomCondition

@RunWith(JUnitPlatform::class)
class BooleanIteratorTest: SubjectSpek<BooleanIterator>(receiving {
    val SIZE = 100
    val list = BooleanLists.mutable.empty().apply {
        repeat(SIZE) {
            add(randomBoolean())
        }
    }
    subject { list.booleanIterator() }
    given("function: iterate with acceptor") {
        it("should iterate all $SIZE elements") {
            var count = 0
            subject.iterate {
                count++
            }
            count shouldEqual SIZE
        }
    }
    given("function: indexedIterate with biAcceptor") {
        it("should iterate all $SIZE elements with valid order") {
            var count = 0
            subject.indexedIterate { i, sh ->
                count++ == i
            }
            count shouldEqual SIZE
        }
    }
    given("function: count with condition") {
        it("should count all satisfied") {
            var should = 0
            subject.count {
                val randomCondition = randomCondition()
                if (randomCondition) should++
                randomCondition
            } shouldEqual should
        }
    }
    given("function: all with condition") {
        it("should return false if one or more elements denied") {
            val counter = Counter(0..SIZE)
            subject.all {
                !counter.isRandom()
            } shouldEqual false
        }
        it("should return true if all elements satisfied") {
            subject.all {
                true
            } shouldEqual true
        }
        it("should return false if all elements denied") {
            subject.all {
                false
            } shouldEqual false
        }
    }
    given("function: none with condition") {
        it("should return false if one or more elements satisfied") {
            val counter = Counter(0..SIZE)
            subject.none {
                counter.isRandom()
            } shouldEqual false
        }
        it("should return false if all elements satisfied") {
            subject.none {
                true
            } shouldEqual false
        }
        it("should return true if all elements denied") {
            subject.none {
                false
            } shouldEqual true
        }
    }
    given("function: any with condition") {
        it("should return true if one or more elements satisfied") {
            val counter = Counter(0..SIZE)
            subject.any {
                counter.isRandom()
            } shouldEqual true
        }
        it("should return true if all elements satisfied") {
            subject.any {
                true
            } shouldEqual true
        }
        it("should return false if all elements denied") {
            subject.any {
                false
            } shouldEqual false
        }
    }
    given("function: single with condition") {
        it("should throw IllegalStateException if none elements satisfied") {
            IllegalStateException::class shouldThrow {
                subject.single { false }
            }
        }
        it("should throw IllegalStateException if too many elements satisfied") {
            IllegalStateException::class shouldThrow {
                subject.single { true }
            }
        }
        it("should return value if one value satisfied") {
            val counter = Counter(0..SIZE)
            subject.single { counter.isRandom() }
        }
    }
    val condition: (Boolean) -> Boolean = {
        val any: Any = it
        when (any) {
            is Boolean -> true
            is Char    -> !any.isLetterOrDigit()
            is Number  -> any.toInt() % 2 == 0
            else       -> false
        }
    }
    val select = list.select(condition)
    given("function: first with condition") {
        it("should throw NoSuchElementException if none elements satisfied") {
            NoSuchElementException::class shouldThrow {
                subject.first { false }
            }
        }
        it("should return first value") {
            subject.first(condition) == select.first
        }
    }
    given("function: last with condition") {
        it("should throw NoSuchElementException if none elements satisfied") {
            NoSuchElementException::class shouldThrow {
                subject.last { false }
            }
        }
        it("should return last value") {
            subject.last(condition) == select.last
        }
    }
    given("function: `index of first` with condition") {
        it("should return -1 if none elements satisfied") {
            subject.`index of first` { false } shouldEqual -1
        }
        it("should return first value") {
            subject.`index of first`(condition) == list.indexOf(select.first)
        }
    }
    given("function: `index of last` with condition") {
        it("should return -1 if none elements satisfied") {
            subject.`index of last` { false } shouldEqual -1
        }

        it("should return first value") {
            subject.`index of last`(condition) == list.lastIndexOf(select.last)
        }
    }
}) {
    class Counter(val expect: Int) {
        constructor(range: IntRange): this(expect = ThreadLocalRandom.current().nextInt(range.first, range.last))

        private var _value = 0
        val value: Int get() = _value.apply {
            _value++
        }

        operator fun contains(index: Int) = value == index
        fun isRandom() = expect in this
    }
}

