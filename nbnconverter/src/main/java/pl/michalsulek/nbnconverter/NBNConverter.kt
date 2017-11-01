package pl.michalsulek.nbnconverter

import kotlin.experimental.and

class NBNConverter {
    companion object {
        private val NUMBER_0 = 0
        private val NUMBER_255 = 255
        private val NUMBER_127 = 127
        private val MASK_LAST_BIT = 0x80
        private val MASK_NON_BIT = 0x0
        private val MASK_ALL_WITHOUT_LAST_BIT = 0x7F
        private val MASK_ALL_BITS = 0xFF

        private fun convertNumberToByte(number: Int) = (number and MASK_ALL_BITS).toByte()
        private fun convertByteToNumber(byte: Byte) = byte.toInt() and MASK_ALL_BITS

        fun convertUnsigned255NumberToByte(number255: Int): Byte {
            if ((number255 < NUMBER_0) or (number255 > NUMBER_255)) {
                throw Exception("Number out of range 0 - 255")
            }

            return convertNumberToByte(number255)
        }

        fun convertByteToUnsigned255Number(byte: Byte): Int {
            return convertByteToNumber(byte)
        }

        fun convertSigned127NumberToByte(number127: Int): Byte {
            if ((number127 < -NUMBER_127) or (number127 > NUMBER_127)) {
                throw Exception("Number out of range -127 - 127")
            }

            val signMask = if (number127 < NUMBER_0) MASK_LAST_BIT else MASK_NON_BIT
            return ((Math.abs(number127) and MASK_ALL_WITHOUT_LAST_BIT) or signMask).toByte()
        }

        fun convertByteToSigned127Number(byte: Byte): Int {
            val sign = (byte and MASK_LAST_BIT.toByte()).toInt() != 0
            var value = convertByteToNumber(byte and MASK_ALL_WITHOUT_LAST_BIT.toByte())
            if (sign) {
                value *= -1
            }
            return value
        }
    }
}