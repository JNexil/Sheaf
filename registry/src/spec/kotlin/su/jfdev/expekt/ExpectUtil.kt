package su.jfdev.expekt

import com.winterbe.expekt.Flavor.*
import su.jfdev.expekt.ExpectCalling.*
import su.jfdev.sheaf.registry.*

fun <T: Identified> expect(registry: Registry<T>) = ExpectRegistry(registry, EXPECT)
fun <T: Identified> expect(identified: T) = ExpectIdentified(identified, EXPECT)
fun <R> expect(calling: Calling<R>): ExpectCalling<R> = ExpectCalling(calling, EXPECT)

val <T: Identified> Registry<T>.should: ExpectRegistry<T> get() = ExpectRegistry(this, SHOULD)
val <T: Identified> T.should: ExpectIdentified<T> get() = ExpectIdentified(this, SHOULD)
val <R> Calling<R>.should: ExpectCalling<R> get() = ExpectCalling(this, SHOULD)


fun <R> expectCalling(callback: () -> R): ExpectCalling<R> = expect(Calling(callback))
