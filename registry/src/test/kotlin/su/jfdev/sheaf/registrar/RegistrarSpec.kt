package su.jfdev.sheaf.registrar

import com.winterbe.expekt.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.jetbrains.spek.api.memoized.CachingMode.*
import org.junit.platform.runner.*
import org.junit.runner.*
import su.jfdev.expekt.*
import su.jfdev.expekt.ExpectCalling.*
import su.jfdev.sheaf.registry.*
import su.jfdev.sheaf.registry.Sample.*
import java.util.*

@RunWith(JUnitPlatform::class)
class RegistrarSpec: SubjectSpek<Registrar<String, Sample>>(spec = {
    fun randomSource() = UUID.randomUUID().toString()
    val INVALID_SOURCE = "invalid"
    val SOURCE = randomSource()
    val SAMPLE = Companion[SOURCE]
    subject(GROUP) {
        Registries.registrar { Companion.maybe(it) }
    }
    describe("empty registrar") {
        it("should has empty registry") {
            subject.registry.should.be.empty
        }
        it("should not contain unknown value or source at sources") {
            subject.sources.should.not.contain.keys("827d0699-c942-42bc-946a-8a4396461b77").and.values(SAMPLE)
        }
//        it("should not contain unknown value or source at sources inverse") {
//            subject.sources.inverse.should.not.contain.keys("827d0699-c942-42bc-946a-8a4396461b77").and.values(SAMPLE)
//        }
        it("should not contain unknown value or uuid at registry") {
            subject.registry.should.not.contain.values(Sample()).and.keys(UUID.randomUUID())
        }
    }
    on("register source") {
        on("transformer return null") {
            test("result should be null") {
                expect(subject register INVALID_SOURCE).`is`.`null`
            }
            it("should not update sources") {
                subject.sources.should.not.keys(INVALID_SOURCE)
            }
        }
        on("transformer return value") {
            test("result should be created by transformer") {
                expect(subject register SOURCE).equal(SAMPLE)
            }
            it("should update sources") {
                subject.sources.should.contain(SOURCE to SAMPLE)
            }
            it("should update registry") {
                subject.registry.should.contain.values(SAMPLE)
            }
        }
        on("register same source") {
            val registration = lazy { subject.sources[SOURCE] }
            it("should throw AlreadyRegisteredException") {
                registration.value
                Calling { subject register SOURCE }.should.fails.with.exception(AlreadyRegisteredException::class)
            }
            it("should not update sources") {
                subject.sources[SOURCE].should.be.identity(registration.value)
            }
            it("should not update registry") {
                subject.registry.should.have.value(SAMPLE.uuid).that.`is`.identity(registration.value)
            }
        }
    }
    on("unregister illegal source") {
        it("should return null") {
            val result = subject unregister INVALID_SOURCE
            result.should.be.`null`
        }
    }
    on("unregister unknown source") {
        it("should return null") {
            val result = subject unregister randomSource()
            result.should.be.`null`
        }
    }
    on("unregister source") {
        it("should return old registration") {
            val result = subject unregister SOURCE
            result.should.be.equal(SAMPLE)
        }
        it("should update sources") {
            subject.sources.should.not.contain.keys(SOURCE).and.values(SAMPLE)
        }
        it("should update registry") {
            subject.registry.should.not.contain.keys(SAMPLE.uuid).and.values(SAMPLE)
        }
    }
    on("unregister unknown registration") {
        it("should return null") {
            val result = subject unregister Sample()
            result.should.be.`null`
        }
    }
    on("unregister registration") {
        val source = randomSource()
        val value = subject.register(source)!!
        it("should return identical value") {
            val result = subject unregister value
            result.should.be.identity(source)
        }
        it("should update sources") {
            subject.sources.should.not.contain.keys(source).and.values(value)
        }
        it("should update registry") {
            subject.registry.should.not.contain.keys(value.uuid).and.values(value)
        }
    }
})

