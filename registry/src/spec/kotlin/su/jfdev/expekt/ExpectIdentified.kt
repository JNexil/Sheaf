package su.jfdev.expekt

import com.winterbe.expekt.*
import su.jfdev.sheaf.registry.*

class ExpectIdentified<T: Identified>(subject: T?, flavor: Flavor): AbstractExpectAny<T, ExpectIdentified<T>>(subject, flavor) {
    val uuid by transit {
        ExpectComparable(subject!!.uuid, flavor)
    }
}