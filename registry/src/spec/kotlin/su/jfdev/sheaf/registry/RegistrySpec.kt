package su.jfdev.sheaf.registry

import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*
import org.mockito.Mockito.*
import su.jfdev.expekt.*
import java.util.*
import kotlin.reflect.*

class RegistrySpec<T: Identified>: SubjectSpek<Registry<T>>(fun SubjectDsl<Registry<T>>.() {
    on("accessing value by uuid") {
        it("should return null when access unknown uuid") {
            val anyUUID = UUID.randomUUID()

            subject.should.have.value(anyUUID).that.`is`.`null`
        }
        it("should return value when access known uuid") {
            val knownValue = subject.first()
            val knownUUID = knownValue.uuid

            subject.should.have.value(knownUUID).that.equal(knownValue)
        }
    }
    on("checking that value contained in registry") {
        should("return false when check unknown value") {
            val knownValue = subject.first()
            val unknownValue = mock(knownValue.javaClass)
            whenever(unknownValue.uuid).thenReturn(UUID.randomUUID())

            unknownValue !in subject
        }
        should("return true when check known value") {
            val knownValue = subject.first()

            knownValue in subject
        }
    }
    on("checking that uuid contained in registry") {
        should("return false when check unknown uuid") {
            val anyUUID = UUID.randomUUID()

            anyUUID !in subject
        }
        should("return true when check known uuid") {
            val knownValue = subject.first()
            val knownUUID = knownValue.uuid

            knownUUID in subject
        }
    }
    given("iterator") {
        on("iterate while has next") {
            it("should have count, that equal size of registry") {
                val count = subject.count()

                subject.should.have.size(count)
            }
        }
    }
    given("isEmpty") {
        it("== true, if registry's empty (size equal zero)") {
            if (subject.size == 0) subject.should.be.empty
        }
        it("== false, if registry isn't empty (size greater than zero)") {
            if (subject.size > 0) subject.should.not.be.empty
        }
    }
})

fun <T: Identified> SubjectDsl<Registry<T>>.`it should test Registry`(factory: () -> Registry<T>) {
    subject(factory = factory)

    @Suppress("UNCHECKED_CAST")
    itBehavesLike(RegistrySpec::class as KClass<RegistrySpec<T>>)
}