package su.jfdev.codegen

import su.jfdev.codegen.generators.*
import java.nio.file.*

fun main(args: Array<String>) = Source(project = args[0].toString(), extension = args[1].toString()).fill()

fun Source.fill() = walkPacks {
    for (type in Source.primitives.keys) {
        Generators.util(resource = resource).generate(
                gen(prefix = type.capitalize(), configuration = Configuration.primitive(type))
                                                     )
    }
}

fun Source.walkPacks(action: Pack.() -> Unit) {
    Files.walk(input.toPath()).parallel().forEach {
        val file = it.toFile()
        if (file.isFile && file.extension == extension) Pack(this, file).action()
    }
}