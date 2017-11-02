package pl.michalsulek.nbnconverter

private const val NUMBER_0 = 0
private const val NUMBER_255 = 255
private const val NUMBER_127 = 127
private const val NUMBER_32767 = 32767
private const val NUMBER_65535 = 65535
private const val NUMBER_2147483647 = 2147483647
private const val MASK_LAST_BIT = 0x80
private const val MASK_NON_BIT = 0x0
private const val MASK_ALL_WITHOUT_LAST_BIT = 0x7F
private const val MASK_ALL_BITS = 0xFF

private fun maskNumberAsByte(number: Int) = number and MASK_ALL_BITS
private fun convertByteToNumber(byte: Byte) = byte.toInt() and MASK_ALL_BITS

fun Int.unsigned255NumberToByte(): Byte {
    if ((this < NUMBER_0) or (this > NUMBER_255)) {
        throw Exception("Number out of range 0 - 255")
    }

    return maskNumberAsByte(this).toByte()
}

fun Byte.byteToUnsigned255Number(): Int {
    return convertByteToNumber(this)
}

fun Int.signed127NumberToByte(): Byte {
    if ((this < -NUMBER_127) or (this > NUMBER_127)) {
        throw Exception("Number out of range -127 - 127")
    }

    val signMask = if (this < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
    return ((Math.abs(this) and MASK_ALL_WITHOUT_LAST_BIT) or signMask).toByte()
}

fun Byte.byteToSigned127Number(): Int {
    val sign = (this.toInt() and MASK_LAST_BIT) != 0
    var value = convertByteToNumber((this.toInt() and MASK_ALL_WITHOUT_LAST_BIT).toByte())
    if (sign) {
        value *= -1
    }
    return value
}

fun Int.unsigned65535NumberToBytes(): ByteArray {
    if ((this < NUMBER_0) or (this > NUMBER_65535)) {
        throw Exception("Number out of range 0 - 65535")
    }

    val firstByte = maskNumberAsByte(this shr 8).toByte()
    val secondByte = maskNumberAsByte(this).toByte()

    return byteArrayOf(firstByte, secondByte)
}

fun ByteArray.bytesToUnsigned65535Number(): Int {
    if (this.isEmpty() or (this.size > 2)) {
        throw Exception("Wrong array size, should have exactly 2 bytes")
    }

    val numberFromFirstByte = convertByteToNumber(this[0]) shl 8
    val numberFromSecondByte = convertByteToNumber(this[1])

    return numberFromFirstByte or numberFromSecondByte
}

fun Int.signed32767ToBytes(): ByteArray {
    if ((this < -NUMBER_32767) or (this > NUMBER_32767)) {
        throw Exception("Number out of range -32767 - 32767")
    }

    val signMask = if (this < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
    val firstByte = maskNumberAsByte(Math.abs(this) shr 8) and MASK_ALL_WITHOUT_LAST_BIT or signMask
    val secondByte = maskNumberAsByte(Math.abs(this))

    return byteArrayOf(firstByte.toByte(), secondByte.toByte())
}

fun ByteArray.bytesToSigned32767Number(): Int {
    if (this.isEmpty() or (this.size > 2)) {
        throw Exception("Wrong array size, should have exactly 2 bytes")
    }

    val sign = (convertByteToNumber(this[0]) and MASK_LAST_BIT) != 0
    val numberFromFirstByte = convertByteToNumber(this[0]) and MASK_ALL_WITHOUT_LAST_BIT shl 8
    val numberFromSecondByte = convertByteToNumber(this[1])
    var value = numberFromFirstByte or numberFromSecondByte
    if (sign) {
        value *= -1
    }

    return value
}

fun Int.signed2147483647ToBytes(): ByteArray {
    if ((this < -NUMBER_2147483647) or (this > NUMBER_2147483647)) {
        throw Exception("Number out of range -2147483647 - 2147483647")
    }

    val signMask = if (this < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
    val firstByte = maskNumberAsByte(Math.abs(this) shr 24) and MASK_ALL_WITHOUT_LAST_BIT or signMask
    val secondByte = maskNumberAsByte(Math.abs(this) shr 16)
    val thirdByte = maskNumberAsByte(Math.abs(this) shr 8)
    val fourthByte = maskNumberAsByte(Math.abs(this))

    return byteArrayOf(firstByte.toByte(), secondByte.toByte(), thirdByte.toByte(), fourthByte.toByte())
}

fun ByteArray.bytesToSigned2147483647Number(): Int {
    if (this.isEmpty() or (this.size > 4)) {
        throw Exception("Wrong array size, should have exactly 4 bytes")
    }

    val sign = (convertByteToNumber(this[0]) and MASK_LAST_BIT) != 0
    val numberFromFirstByte = convertByteToNumber(this[0]) and MASK_ALL_WITHOUT_LAST_BIT shl 24
    val numberFromSecondByte = convertByteToNumber(this[1]) shl 16
    val numberFromThirdByte = convertByteToNumber(this[2]) shl 8
    val numberFromFourthByte = convertByteToNumber(this[3])
    var value = numberFromFirstByte or numberFromSecondByte or numberFromThirdByte or numberFromFourthByte
    if (sign) {
        value *= -1
    }

    return value
}