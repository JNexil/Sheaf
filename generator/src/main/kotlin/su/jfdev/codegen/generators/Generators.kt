package su.jfdev.codegen.generators

import java.io.*

object Generators {
    fun flat(resource: File) = Generator {
        resource.forEachLine {
            +it
        }
        println("done: [$resource] -> [${it.pack.output}]\n")
    }
}