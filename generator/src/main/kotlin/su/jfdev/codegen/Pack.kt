package su.jfdev.codegen

import su.jfdev.codegen.generators.*
import java.io.*

class Pack(val source: Source,
           val resource: File,
           val name: String = resource.name.substringBeforeLast(source.extension),
           val output: File = source.output + (resource.parentFile - source.input)) {

    fun gen(prefix: String = "", postfix: String = "", configuration: Configuration)
            = Gen(configuration = configuration, pack = this, name = prefix + name.capitalize() + postfix)

    companion object {
        private operator fun File.plus(file: File) = File(this, file.path)
        private operator fun File.minus(file: File) = relativeTo(file)
    }
}