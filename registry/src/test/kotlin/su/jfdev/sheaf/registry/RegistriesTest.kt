package su.jfdev.sheaf.registry

import com.winterbe.expekt.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.junit.platform.runner.*
import org.junit.runner.*
import java.util.*

@RunWith(JUnitPlatform::class)
class RegistriesTest: Spek(spec = {
    on("create registry from map: about Map") {
        it("should return registry, that is view of map") {
            val map = HashMap<UUID, Sample>()
            val registry: Registry<Sample> = Registries about map
            val sample = Sample()
            registry[sample.uuid].should.be.`null`
            map[sample.uuid] = sample
            registry[sample.uuid].should.equal(sample)
        }
    }
    on("create registry from map: copy Map") {
        it("should return registry, that is copy of map") {
            val map = HashMap<UUID, Sample>()
            val registry: Registry<Sample> = Registries copy map
            val sample = Sample()
            registry[sample.uuid].should.be.`null`
            map[sample.uuid] = sample
            registry[sample.uuid].should.be.`null`
        }
    }
    on("copy registry from map: copy Registry") {
        it("should return registry, that is snapshot of given registry, but can't be modified") {
            val map = HashMap<UUID, Sample>()
            val registry: Registry<Sample> = Registries.copy(Registries about map)
            val sample = Sample()
            registry[sample.uuid].should.be.`null`
            map[sample.uuid] = sample
            registry[sample.uuid].should.be.`null`
        }
    }
})

