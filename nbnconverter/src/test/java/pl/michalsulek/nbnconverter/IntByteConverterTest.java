package pl.michalsulek.nbnconverter;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntByteConverterTest {

    private int convertUnsigned255Number(int inNumber) {
        byte b = IntByteConverterKt.unsigned255ToByte(inNumber);
        return IntByteConverterKt.toUnsigned255Int(b);
    }

    private int convertSigned127Number(int inNumber) {
        byte b = IntByteConverterKt.signed127ToByte(inNumber);
        return IntByteConverterKt.toSigned127Number(b);
    }

    private int convertUnsigned65535Number(int inNumber) {
        byte[] b = IntByteConverterKt.unsigned65535ToBytes(inNumber);
        return IntByteConverterKt.toUnsigned65535Number(b);
    }

    private int convertSigned32767Number(int inNumber) {
        byte[] b = IntByteConverterKt.signed32767ToBytes(inNumber);
        return IntByteConverterKt.toSigned32767Number(b);
    }

    private int convertSigned2147483647Number(int inNumber) {
        byte[] b = IntByteConverterKt.intToBytes(inNumber);
        return IntByteConverterKt.bytesToInt(b);
    }

    @Test
    public void testUnsigned255NumberConvert_0_expectSuccess() {
        int inNumber = 0;
        int outNumber = convertUnsigned255Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned255NumberConvert_100_expectSuccess() {
        int inNumber = 100;
        int outNumber = convertUnsigned255Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned255NumberConvert_255_expectSuccess() {
        int inNumber = 255;
        int outNumber = convertUnsigned255Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned255NumberConvert_300_expectFailure() {
        int inNumber = 300;
        try {
            convertUnsigned255Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testUnsigned255NumberConvert_minus300_expectFailure() {
        int inNumber = -300;
        try {
            convertUnsigned255Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testSigned127NumberConvert_minus127_expectSuccess() {
        int inNumber = -127;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_minus50_expectSuccess() {
        int inNumber = -50;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_0_expectSuccess() {
        int inNumber = 0;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_50_expectSuccess() {
        int inNumber = 50;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_127_expectSuccess() {
        int inNumber = 127;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_minus128_expectFailure() {
        int inNumber = -128;
        try {
            convertSigned127Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testSigned127NumberConvert_128_expectFailure() {
        int inNumber = 128;
        try {
            convertSigned127Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testUnsigned65536NumberConvert_0_expectSuccess() {
        int inNumber = 0;
        int outNumber = convertUnsigned65535Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned65536NumberConvert_10000_expectSuccess() {
        int inNumber = 10000;
        int outNumber = convertUnsigned65535Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned65536NumberConvert_65535_expectSuccess() {
        int inNumber = 65535;
        int outNumber = convertUnsigned65535Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned65536NumberConvert_minus1_expectFailure() {
        int inNumber = -1;
        try {
            convertUnsigned65535Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testUnsigned65536NumberConvert_65536_expectFailure() {
        int inNumber = 65536;
        try {
            convertUnsigned65535Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testSigned32767NumberConvert_minus32767_expectSuccess() {
        int inNumber = -32767;
        int outNumber = convertSigned32767Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned32767NumberConvert_minus777_expectSuccess() {
        int inNumber = -777;
        int outNumber = convertSigned32767Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned32767NumberConvert_0_expectSuccess() {
        int inNumber = 0;
        int outNumber = convertSigned32767Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned32767NumberConvert_8888_expectSuccess() {
        int inNumber = 8888;
        int outNumber = convertSigned32767Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned32767NumberConvert_32767_expectSuccess() {
        int inNumber = 32767;
        int outNumber = convertSigned32767Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned32767NumberConvert_minus32768_expectFailure() {
        int inNumber = -32768;
        try {
            convertSigned32767Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testSigned32767NumberConvert_32768_expectFailure() {
        int inNumber = 32768;
        try {
            convertSigned32767Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

    @Test
    public void testSigned2147483647NumberConvert_minus2147483647_expectSuccess() {
        int inNumber = -2147483647;
        int outNumber = convertSigned2147483647Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned2147483647NumberConvert_minus1_expectSuccess() {
        int inNumber = -1;
        int outNumber = convertSigned2147483647Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned2147483647NumberConvert_0_expectSuccess() {
        int inNumber = 0;
        int outNumber = convertSigned2147483647Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned2147483647NumberConvert_1_expectSuccess() {
        int inNumber = 1;
        int outNumber = convertSigned2147483647Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned2147483647NumberConvert_2147483647_expectSuccess() {
        int inNumber = 2147483647;
        int outNumber = convertSigned2147483647Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned2147483647NumberConvert_2147483648_expectFailure() {
        int inNumber = -2147483648;
        try {
            convertSigned2147483647Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }
}