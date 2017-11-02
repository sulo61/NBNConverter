package pl.michalsulek.nbnconverter

const val NUMBER_0 = 0
const val NUMBER_255 = 255
const val NUMBER_127 = 127
const val NUMBER_32767 = 32767
const val NUMBER_65535 = 65535
const val NUMBER_2147483647 = 2147483647

const val INT_MASK_LAST_BIT = 0x80
const val INT_MASK_NON_BIT = 0x0
const val INT_MASK_ALL_WITHOUT_LAST_BIT = 0x7F
const val INT_MASK_ALL_BITS = 0xFF

fun maskIntAsByte(number: Int) = number and INT_MASK_ALL_BITS
fun convertByteToInt(byte: Byte) = byte.toInt() and INT_MASK_ALL_BITS

fun Int.unsigned255ToByte(): Byte {
    if ((this < NUMBER_0) or (this > NUMBER_255)) {
        throw Exception("Number out of range 0 - 255")
    }

    return maskIntAsByte(this).toByte()
}

fun Byte.toUnsigned255Int(): Int {
    return convertByteToInt(this)
}

fun Int.signed127ToByte(): Byte {
    if ((this < -NUMBER_127) or (this > NUMBER_127)) {
        throw Exception("Number out of range -127 - 127")
    }

    val signMask = if (this < NUMBER_0) INT_MASK_LAST_BIT else INT_MASK_NON_BIT
    return ((Math.abs(this) and INT_MASK_ALL_WITHOUT_LAST_BIT) or signMask).toByte()
}

fun Byte.toSigned127Number(): Int {
    val sign = (this.toInt() and INT_MASK_LAST_BIT) != 0
    var value = convertByteToInt((this.toInt() and INT_MASK_ALL_WITHOUT_LAST_BIT).toByte())
    if (sign) {
        value *= -1
    }
    return value
}

fun Int.unsigned65535ToBytes(): ByteArray {
    if ((this < NUMBER_0) or (this > NUMBER_65535)) {
        throw Exception("Number out of range 0 - 65535")
    }

    val firstByte = maskIntAsByte(this shr 8).toByte()
    val secondByte = maskIntAsByte(this).toByte()

    return byteArrayOf(firstByte, secondByte)
}

fun ByteArray.toUnsigned65535Number(): Int {
    if (this.isEmpty() or (this.size > 2)) {
        throw Exception("Wrong array size, should have exactly 2 bytes")
    }

    val numberFromFirstByte = convertByteToInt(this[0]) shl 8
    val numberFromSecondByte = convertByteToInt(this[1])

    return numberFromFirstByte or numberFromSecondByte
}

fun Int.signed32767ToBytes(): ByteArray {
    if ((this < -NUMBER_32767) or (this > NUMBER_32767)) {
        throw Exception("Number out of range -32767 - 32767")
    }

    val signMask = if (this < NUMBER_0) INT_MASK_LAST_BIT else INT_MASK_NON_BIT
    val firstByte = maskIntAsByte(Math.abs(this) shr 8) and INT_MASK_ALL_WITHOUT_LAST_BIT or signMask
    val secondByte = maskIntAsByte(Math.abs(this))

    return byteArrayOf(firstByte.toByte(), secondByte.toByte())
}

fun ByteArray.toSigned32767Number(): Int {
    if (this.isEmpty() or (this.size > 2)) {
        throw Exception("Wrong array size, should have exactly 2 bytes")
    }

    val sign = (convertByteToInt(this[0]) and INT_MASK_LAST_BIT) != 0
    val numberFromFirstByte = convertByteToInt(this[0]) and INT_MASK_ALL_WITHOUT_LAST_BIT shl 8
    val numberFromSecondByte = convertByteToInt(this[1])
    var value = numberFromFirstByte or numberFromSecondByte
    if (sign) {
        value *= -1
    }

    return value
}

fun Int.intToBytes(): ByteArray {
    if ((this < -NUMBER_2147483647) or (this > NUMBER_2147483647)) {
        throw Exception("Number out of range -2147483647 - 2147483647")
    }

    val signMask = if (this < NUMBER_0) INT_MASK_LAST_BIT else INT_MASK_NON_BIT
    val firstByte = maskIntAsByte(Math.abs(this) shr 24) and INT_MASK_ALL_WITHOUT_LAST_BIT or signMask
    val secondByte = maskIntAsByte(Math.abs(this) shr 16)
    val thirdByte = maskIntAsByte(Math.abs(this) shr 8)
    val fourthByte = maskIntAsByte(Math.abs(this))

    return byteArrayOf(firstByte.toByte(), secondByte.toByte(), thirdByte.toByte(), fourthByte.toByte())
}

fun ByteArray.bytesToInt(): Int {
    if (this.isEmpty() or (this.size > 4)) {
        throw Exception("Wrong array size, should have exactly 4 bytes")
    }

    val sign = (convertByteToInt(this[0]) and INT_MASK_LAST_BIT) != 0
    val numberFromFirstByte = convertByteToInt(this[0]) and INT_MASK_ALL_WITHOUT_LAST_BIT shl 24
    val numberFromSecondByte = convertByteToInt(this[1]) shl 16
    val numberFromThirdByte = convertByteToInt(this[2]) shl 8
    val numberFromFourthByte = convertByteToInt(this[3])
    var value = numberFromFirstByte or numberFromSecondByte or numberFromThirdByte or numberFromFourthByte
    if (sign) {
        value *= -1
    }

    return value
}