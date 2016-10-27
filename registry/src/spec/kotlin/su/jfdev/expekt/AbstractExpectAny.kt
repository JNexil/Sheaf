package su.jfdev.expekt

import com.winterbe.expekt.*
import java.util.*
import kotlin.properties.*
import kotlin.reflect.*

abstract class AbstractExpectAny<I, T: AbstractExpectAny<I, T>>(subject: I?, flavor: Flavor): ExpectAny<I>(subject, flavor) {
    override val a: T get() = me { super.a }
    override val an: T get() = me { super.an }
    override val and: T get() = me { super.and }
    override val at: T get() = me { super.at }
    override val be: T get() = me { super.be }
    override val been: T get() = me { super.been }
    override val has: T get() = me { super.has }
    override val have: T get() = me { super.have }
    override val `is`: T get() = me { super.`is` }
    override val not: T get() = me { super.not }
    override val `null`: T get() = me { super.`null` }
    override val of: T get() = me { super.of }
    override val same: T get() = me { super.same }
    override val that: T get() = me { super.that }
    override val the: T get() = me { super.the }
    override val to: T get() = me { super.to }
    override val which: T get() = me { super.which }
    override val with: T get() = me { super.with }
    override fun equal(expected: I?): T = me { super.equal(expected) }
    override fun identity(expected: I?): T = me { super.identity(expected) }
    override fun <S: I> instanceof(type: Class<S>): T = me { super.instanceof(type) }
    override fun satisfy(predicate: (I) -> Boolean): T = me { super.satisfy(predicate) }

    @Suppress("UNCHECKED_CAST")
    protected inline fun me(action: () -> Unit): T = apply { action() } as T

    protected val words: MutableList<String> = findWords()

    protected fun ExpectAny<*>.findWords(): MutableList<String> = ExpectAny::class.java.methods
            .filter { it.returnType == ArrayList::class.java && it.parameterTypes.isEmpty() }
            .map { it.invoke(this) }
            .filterIsInstance<MutableList<String>>()
            .single()

    protected operator fun Any?.unaryPlus(): Unit {
        words += toString()
    }

    protected inline fun action(crossinline action: () -> Unit) = object: ReadOnlyProperty<T, T> {
        override fun getValue(thisRef: T, property: KProperty<*>): T = thisRef.apply {
            words += property.name
            action()
        }
    }

    protected inline fun verified(crossinline verify: I.() -> Boolean) = action {
        verify {
            subject!!.verify()
        }
    }

    protected fun <S: ExpectAny<*>> transit(action: () -> S) = object: ReadOnlyProperty<T, S> {
        override fun getValue(thisRef: T, property: KProperty<*>): S = action().transit()
    }

    protected fun <S: ExpectAny<*>> S.transit() = apply {
        words.add("size")
        copyNegative()
        replaceWords()
    }

    private fun ExpectAny<*>.replaceWords() {
        findWords().apply {
            clear()
            addAll(words)
        }
    }

    private fun ExpectAny<*>.copyNegative() {
        val negative = words.count { it == "not" } % 2 != 0
        if (negative) not
    }
}