package su.jfdev.expekt

import com.winterbe.expekt.*
import com.winterbe.expekt.Flavor.*
import su.jfdev.expekt.ExpectCalling.*
import java.time.*
import kotlin.reflect.*
import kotlin.system.*

class ExpectCalling<R>(subject: Calling<R>, flavor: Flavor): AbstractExpectAny<Calling<R>, ExpectCalling<R>>(subject, flavor) {
    fun exception(type: KClass<out Exception>) = apply {
        +"exception"; +type
        verify {
            type.java.isInstance(subject!!.exception)
        }
    }

    val NPE: ExpectCalling<R> get() = exception(NullPointerException::class)

    val fails by verified {
        !isSuccessful
    }

    val done by verified {
        isSuccessful
    }

    val time by transit {
        ExpectComparable(subject.duration, SHOULD)
    }

    val exception by transit {
        ExpectAny(subject.exception, SHOULD)
    }

    val result by transit {
        ExpectAny(subject.value, SHOULD)
    }

    class Calling<R>(callable: () -> R) {
        val duration: Duration
        var exception: Exception? = null; private set
        var value: R? = null; private set

        init {
            val nanos = measureNanoTime {
                try {
                    value = callable()
                } catch (e: Exception) {
                    exception = e
                }
            }
            duration = Duration.ofNanos(nanos)
        }

        val isSuccessful: Boolean = exception == null

        override fun toString() = when (exception) {
            null -> "[Since $duration: $value]"
            else -> "[Fail since $duration: $exception]"
        }
    }
}