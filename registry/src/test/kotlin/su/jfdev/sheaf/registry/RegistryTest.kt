package su.jfdev.sheaf.registry

import org.jetbrains.spek.api.*
import org.junit.platform.runner.*
import org.junit.runner.*
import java.util.*

fun generateMap(limit: Int = 100) = generateSequence(UUID::randomUUID).take(limit).associateBy({ it }, ::Sample)

@RunWith(JUnitPlatform::class)
class RegistryTestAbout: SubjectSpek<Registry<Sample>>(spec = {
    `it should test Registry` {
        Registries about generateMap()
    }
})

@RunWith(JUnitPlatform::class)
class RegistryTestCopyMap: SubjectSpek<Registry<Sample>>(spec = {
    `it should test Registry` {
        Registries copy generateMap()
    }
})

@RunWith(JUnitPlatform::class)
class RegistryTestCopyRegistry: SubjectSpek<Registry<Sample>>(spec = {
    `it should test Registry` {
        Registries copy (Registries about generateMap())
    }
})