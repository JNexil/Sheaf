package su.jfdev.ekollections.util

import java.util.concurrent.*

object Primitives {
    const val NULL_Boolean: Boolean = false
    const val NULL_Char: Char = ' '
    const val NULL_Double: Double = .0
    const val NULL_Float: Float = 0F
    const val NULL_Int: Int = 0
    const val NULL_Long: Long = 0L
    const val NULL_Short: Short = 0
    const val NULL_Byte: Byte = 0

    private val random: ThreadLocalRandom = ThreadLocalRandom.current()

    fun randomBoolean() = random.nextBoolean()
    fun randomChar() = randomNumber(0, Character.MAX_VALUE.toInt()).toByte().toChar()
    fun randomDouble() = random.nextDouble()
    fun randomFloat() = random.nextFloat()
    fun randomInt() = random.nextInt()
    fun randomLong() = random.nextLong()
    fun randomShort() = randomNumber(Short.MIN_VALUE, Short.MAX_VALUE).toShort()
    fun randomByte() = randomNumber(Byte.MIN_VALUE, Byte.MAX_VALUE).toByte()
    fun randomNumber(min: Number, max: Number): Number = random.nextInt(min.toInt(), max.toInt() + 1)
}