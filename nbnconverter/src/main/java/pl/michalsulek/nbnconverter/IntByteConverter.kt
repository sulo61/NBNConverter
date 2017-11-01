package pl.michalsulek.nbnconverter

class IntByteConverter {
    companion object {

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

        fun unsigned255NumberToByte(number255: Int): Byte {
            if ((number255 < NUMBER_0) or (number255 > NUMBER_255)) {
                throw Exception("Number out of range 0 - 255")
            }

            return maskNumberAsByte(number255).toByte()
        }

        fun byteToUnsigned255Number(byte: Byte): Int {
            return convertByteToNumber(byte)
        }

        fun signed127NumberToByte(number127: Int): Byte {
            if ((number127 < -NUMBER_127) or (number127 > NUMBER_127)) {
                throw Exception("Number out of range -127 - 127")
            }

            val signMask = if (number127 < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
            return ((Math.abs(number127) and MASK_ALL_WITHOUT_LAST_BIT) or signMask).toByte()
        }

        fun byteToSigned127Number(byte: Byte): Int {
            val sign = (byte.toInt() and MASK_LAST_BIT) != 0
            var value = convertByteToNumber((byte.toInt() and MASK_ALL_WITHOUT_LAST_BIT).toByte())
            if (sign) {
                value *= -1
            }
            return value
        }

        fun unsigned65535NumberToBytes(number65535: Int): ByteArray {
            if ((number65535 < NUMBER_0) or (number65535 > NUMBER_65535)) {
                throw Exception("Number out of range 0 - 65535")
            }

            val firstByte = maskNumberAsByte(number65535 shr 8).toByte()
            val secondByte = maskNumberAsByte(number65535).toByte()

            return byteArrayOf(firstByte, secondByte)
        }

        fun bytesToUnsigned65535Number(byteArray: ByteArray): Int {
            if (byteArray.isEmpty() or (byteArray.size > 2)) {
                throw Exception("Wrong array size, should have exactly 2 bytes")
            }

            val numberFromFirstByte = convertByteToNumber(byteArray[0]) shl 8
            val numberFromSecondByte = convertByteToNumber(byteArray[1])

            return numberFromFirstByte or numberFromSecondByte
        }

        fun signed32767ToBytes(number32767: Int): ByteArray {
            if ((number32767 < -NUMBER_32767) or (number32767 > NUMBER_32767)) {
                throw Exception("Number out of range -32767 - 32767")
            }

            val signMask = if (number32767 < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
            val firstByte = maskNumberAsByte(Math.abs(number32767) shr 8) and MASK_ALL_WITHOUT_LAST_BIT or signMask
            val secondByte = maskNumberAsByte(Math.abs(number32767))

            return byteArrayOf(firstByte.toByte(), secondByte.toByte())
        }

        fun bytesToSigned32767Number(byteArray: ByteArray): Int {
            if (byteArray.isEmpty() or (byteArray.size > 2)) {
                throw Exception("Wrong array size, should have exactly 2 bytes")
            }

            val sign = (convertByteToNumber(byteArray[0]) and MASK_LAST_BIT) != 0
            val numberFromFirstByte = convertByteToNumber(byteArray[0]) and MASK_ALL_WITHOUT_LAST_BIT shl 8
            val numberFromSecondByte = convertByteToNumber(byteArray[1])
            var value = numberFromFirstByte or numberFromSecondByte
            if (sign) {
                value *= -1
            }

            return value
        }

        fun signed2147483647ToBytes(number2147483647: Int): ByteArray {
            if ((number2147483647 < -NUMBER_2147483647) or (number2147483647 > NUMBER_2147483647)) {
                throw Exception("Number out of range -2147483647 - 2147483647")
            }

            val signMask = if (number2147483647 < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
            val firstByte = maskNumberAsByte(Math.abs(number2147483647) shr 24) and MASK_ALL_WITHOUT_LAST_BIT or signMask
            val secondByte = maskNumberAsByte(Math.abs(number2147483647) shr 16)
            val thirdByte = maskNumberAsByte(Math.abs(number2147483647) shr 8)
            val fourthByte = maskNumberAsByte(Math.abs(number2147483647))

            return byteArrayOf(firstByte.toByte(), secondByte.toByte(), thirdByte.toByte(), fourthByte.toByte())
        }

        fun bytesToSigned2147483647Number(byteArray: ByteArray): Int {
            if (byteArray.isEmpty() or (byteArray.size > 4)) {
                throw Exception("Wrong array size, should have exactly 4 bytes")
            }

            val sign = (convertByteToNumber(byteArray[0]) and MASK_LAST_BIT) != 0
            val numberFromFirstByte = convertByteToNumber(byteArray[0]) and MASK_ALL_WITHOUT_LAST_BIT shl 24
            val numberFromSecondByte = convertByteToNumber(byteArray[1]) shl 16
            val numberFromThirdByte = convertByteToNumber(byteArray[2]) shl 8
            val numberFromFourthByte = convertByteToNumber(byteArray[3])
            var value = numberFromFirstByte or numberFromSecondByte or numberFromThirdByte or numberFromFourthByte
            if (sign) {
                value *= -1
            }

            return value
        }
    }
}