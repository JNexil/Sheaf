package su.jfdev.codegen.generators

import su.jfdev.codegen.*

data class Configuration(val author: String, val replacements: Iterable<Pair<String, String>> = emptyList(), val extension: String = ".kt") {
    companion object {
        fun primitive(name: String) = Configuration("Jamefrus", replacements = Source.primitives[name]!!.toList())
    }
}
