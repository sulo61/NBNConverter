package pl.michalsulek.nbnconverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LongByteConverterTest {

    private long convertLong(long inNumber) {
        byte[] b = LongByteConverterKt.longToBytes(inNumber);
        return LongByteConverterKt.bytesToLong(b);
    }

    @Test
    public void testLong_minus9223372036854775807_expectSuccess() {
        long inNumber = -9223372036854775807L;
        long outNumber = convertLong(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testLong_minus1234567_expectSuccess() {
        long inNumber = -1234567;
        long outNumber = convertLong(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testLong_0_expectSuccess() {
        long inNumber = 0;
        long outNumber = convertLong(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testLong_1234567_expectSuccess() {
        long inNumber = 1234567;
        long outNumber = convertLong(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testLong_9223372036854775807_expectSuccess() {
        long inNumber = 9223372036854775807L;
        long outNumber = convertLong(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testLong_minus9223372036854775808_expectSuccess() {
        long inNumber = -9223372036854775808L;
        try {
            convertLong(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

}