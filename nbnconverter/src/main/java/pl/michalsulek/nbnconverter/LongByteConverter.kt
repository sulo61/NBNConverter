package pl.michalsulek.nbnconverter

const val NUMBER_9223372036854775807 = 9223372036854775807L

const val LONG_MASK_LAST_BIT = 0x80.toLong()
const val LONG_MASK_NON_BIT = 0x0.toLong()
const val LONG_MASK_ALL_WITHOUT_LAST_BIT = 0x7F.toLong()
const val LONG_MASK_ALL_BITS = 0xFF.toLong()

fun maskLongAsByte(number: Long) = number and LONG_MASK_ALL_BITS
fun convertByteToLong(byte: Byte) = byte.toLong() and LONG_MASK_ALL_BITS

fun Long.longToBytes(): ByteArray {
    if ((this < -NUMBER_9223372036854775807) or (this > NUMBER_9223372036854775807)) {
        throw Exception("Number out of range -9223372036854775807 - 9223372036854775807")
    }

    val signMask = if (this < NUMBER_0) LONG_MASK_LAST_BIT else LONG_MASK_NON_BIT
    val tmpValue = Math.abs(this)
    val firstByte =  maskLongAsByte((tmpValue shr 56)) and LONG_MASK_ALL_WITHOUT_LAST_BIT or signMask
    val secondByte =  maskLongAsByte((tmpValue shr 48))
    val thirdByte =  maskLongAsByte((tmpValue shr 40))
    val fourthByte =  maskLongAsByte((tmpValue shr 32))
    val fifthByte =  maskLongAsByte((tmpValue shr 24))
    val sixthByte =  maskLongAsByte((tmpValue shr 16))
    val seventhByte =  maskLongAsByte((tmpValue shr 8))
    val eighthByte =  maskLongAsByte(tmpValue)

    return byteArrayOf(
            firstByte.toByte(),
            secondByte.toByte(),
            thirdByte.toByte(),
            fourthByte.toByte(),
            fifthByte.toByte(),
            sixthByte.toByte(),
            seventhByte.toByte(),
            eighthByte.toByte())
}

fun ByteArray.bytesToLong(): Long {
    if (this.isEmpty() or (this.size > 8)) {
        throw Exception("Wrong array size, should have exactly 8 bytes")
    }

    val sign = (convertByteToLong(this[0]) and LONG_MASK_LAST_BIT) != 0L

    val numberFromFirstByte = ((convertByteToInt(this[0]).toLong() and LONG_MASK_ALL_WITHOUT_LAST_BIT) shl 56)
    val numberFromSecondByte = (convertByteToInt(this[1]).toLong() shl 48)
    val numberFromThirdByte = (convertByteToInt(this[2]).toLong() shl 40)
    val numberFromFourthByte = (convertByteToInt(this[3]).toLong() shl 32)
    val numberFromFifthByte = (convertByteToInt(this[4]).toLong() shl 24)
    val numberFromSixthByte = (convertByteToInt(this[5]).toLong() shl 16)
    val numberFromSeventhByte = (convertByteToInt(this[6]).toLong() shl 8)
    val numberFromEighthByte = (convertByteToInt(this[7])).toLong()

    var value = numberFromFirstByte or
            numberFromSecondByte or
            numberFromThirdByte or
            numberFromFourthByte or
            numberFromFifthByte or
            numberFromSixthByte or
            numberFromSeventhByte or
            numberFromEighthByte

    if (sign) {
        value *= -1
    }

    return value
}
