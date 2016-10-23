package su.jfdev.codegen.generators

import java.io.*

object Generators {
    fun util(resource: File) = util { resource.readText() }
    fun util(resource: () -> String) = multi {
        pack.name + "Util" to resource()
    }

    inline fun multi(resource: File, crossinline name: Gen.() -> String) = multi {
        name() to resource.readText()
    }

    inline fun multi(crossinline nameWithResource: Gen.() -> Pair<String, String>) = Generator {
        val (name, resource) = nameWithResource(it)
        multifile(name)
        suppress("NOTHING_TO_INLINE")
        _package()
        +""
        author()
        +""
        +resource
    }

    fun flat(file: File) = Generator {
        file.forEachLine {
            +it
        }
    }
}