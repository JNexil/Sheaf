package su.jfdev.expekt

import com.winterbe.expekt.*
import su.jfdev.sheaf.registry.*
import java.util.*

class ExpectRegistry<T: Identified>(subject: Registry<T>?, flavor: Flavor): AbstractExpectAny<Registry<T>, ExpectRegistry<T>>(subject, flavor) {
    private var anyMode = false

    private var haveMode = false

    val any: ExpectRegistry<T> by action {
        anyMode = true
    }

    val all: ExpectRegistry<T> by action {
        anyMode = false
    }

    override val have: ExpectRegistry<T> by action {
        haveMode = true
    }

    val contain: ExpectRegistry<T> by action {
        haveMode = false
    }

    fun keys(vararg keys: UUID) = apply {
        +"keys"
        +keys.toList().toString()
        when {
            anyMode -> verify { containsAnyKeys(keys) }
            else    -> verify { containsAllKeys(keys) }
        }
    }

    fun values(vararg values: T) = apply {
        +"values"
        +values.toList().toString()
        when {
            anyMode -> verify { containsAnyValues(values) }
            else    -> verify { containsAllValues(values) }
        }
    }

    private fun containsAllKeys(keys: Array<out UUID>): Boolean {
        val validHave = !haveMode || keys.size == subject!!.size
        return validHave && keys.all {
            it in subject!!
        }
    }

    private fun containsAnyKeys(keys: Array<out UUID>) = keys.any {
        it in subject!!
    }

    private fun containsAllValues(values: Array<out T>): Boolean {
        val validHave = !haveMode || values.size == subject!!.size
        return validHave && values.all {
            it in subject!!
        }
    }

    private fun containsAnyValues(values: Array<out T>) = values.any {
        it in subject!!
    }

    val empty by verified(Registry<T>::isEmpty)

    val size by transit {
        ExpectComparable(subject!!.size, flavor)
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

    fun size(size: Int) = apply {
        +"size"
        +size.toString()
        verify { subject!!.size == size }
    }

    fun value(uuid: UUID) = ExpectIdentified(subject!![uuid], flavor).transit()
}