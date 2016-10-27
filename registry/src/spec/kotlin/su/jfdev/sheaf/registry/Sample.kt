package su.jfdev.sheaf.registry

import su.jfdev.anci.util.*
import java.util.*

open class Sample(override val uuid: UUID = UUID.randomUUID()): Identified {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Sample) return false

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int = uuid.hashCode()

    override fun toString(): String = "Sample(uuid=$uuid)"

    companion object {
        @JvmStatic operator fun get(uuid: String) = Sample(uuid = UUID.fromString(uuid))
        @JvmStatic fun maybe(uuid: String): Sample? {
            val uuidObj = orNull { UUID.fromString(uuid) } ?: return null
            return Sample(uuidObj)
        }
    }
}