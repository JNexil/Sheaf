@file:Suppress("NOTHING_TO_INLINE")

package su.jfdev.ekollections.util

inline fun <T> receiving(noinline obj: T.() -> Unit) = obj