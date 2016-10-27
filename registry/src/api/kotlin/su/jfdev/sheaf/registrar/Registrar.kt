package su.jfdev.sheaf.registrar

import su.jfdev.sheaf.registry.*

interface Registrar<S, R: Identified> {
    val sources: Map<S, R>
    val registry: Registry<R>

    infix fun register(source: S): R?
    infix fun unregister(source: S): R?
    infix fun unregister(registration: R): S?
}