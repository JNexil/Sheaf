package su.jfdev.codegen.generators

import java.io.*

object Generators {
    fun util(resource: File) = util { resource.readText() }
    fun util(resource: () -> String) = Generator {
        multifile(it.pack.name + "Util")
        suppress("NOTHING_TO_INLINE")
        _package()
        author()
        +""
        +resource()
    }
}