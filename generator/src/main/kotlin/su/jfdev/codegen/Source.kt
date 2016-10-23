package su.jfdev.codegen

import java.io.*

class Source(val project: String,
             val extension: String = ".gen.kt",
             `in`: String = "templates/src",
             `out`: String = "library/src") {
    val output: File
    val input: File

    init {
        val project = File(project)
        output = File(project, `out`).relativeTo(project)
        input = File(project, `in`).relativeTo(project)
    }

    companion object {
        val primitives = mapOf("int" to mapOf("#PRIM#" to "Int",
                                              "#prim#" to "int"),

                               "byte" to mapOf("#PRIM#" to "Byte",
                                               "#prim#" to "byte"),

                               "long" to mapOf("#PRIM#" to "Long",
                                               "#prim#" to "long"),

                               "boolean" to mapOf("#PRIM#" to "Boolean",
                                                  "#prim#" to "boolean"),

                               "short" to mapOf("#PRIM#" to "Short",
                                                "#prim#" to "short"),

                               "double" to mapOf("#PRIM#" to "Double",
                                                 "#prim#" to "double"),

                               "float" to mapOf("#PRIM#" to "Float",
                                                "#prim#" to "float"),

                               "char" to mapOf("#PRIM#" to "Char",
                                               "#prim#" to "char"))
    }
}