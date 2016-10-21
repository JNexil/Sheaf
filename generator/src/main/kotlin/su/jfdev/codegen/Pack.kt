package su.jfdev.codegen

import su.jfdev.codegen.generators.*
import java.io.*

class Pack(val source: Source,
           val resource: File,
           val name: String = resource.nameWithoutExtension,
           val set: String = (resource.parentFile - source.input).path.substringBefore("/").substringBefore("\\"),
           val pack: File = resource.parentFile - File(source.input, set),
           val output: File = source.output + set + "codegen" + pack.path,
           val `package`: String = pack.path.replace('/', '.').replace('\\', '.')
          ) {

    fun gen(prefix: String = "", postfix: String = "", configuration: Configuration) = Gen(configuration = configuration,
                                                                 pack = this,
                                                                 name = prefix + name.capitalize() + postfix)

    companion object {
        private operator fun File.plus(name: String) = File(this, name)
        private operator fun File.minus(file: File) = relativeTo(file)
    }
}