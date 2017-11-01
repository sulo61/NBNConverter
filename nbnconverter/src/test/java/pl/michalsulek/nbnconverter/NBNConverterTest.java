package pl.michalsulek.nbnconverter;

import org.junit.Test;

import static org.junit.Assert.*;

public class NBNConverterTest {

    private int convertUnsigned255Number(int inNumber) {
        byte b = NBNConverter.Companion.convertUnsigned255NumberToByte(inNumber);
        return NBNConverter.Companion.convertByteToUnsigned255Number(b);
    }

    private int convertSigned127Number(int inNumber) {
        byte b = NBNConverter.Companion.convertSigned127NumberToByte(inNumber);
        return NBNConverter.Companion.convertByteToSigned127Number(b);
    }

    @Test
    public void testUnsigned255NumberConvert_0_expect_success() {
        int inNumber = 0;
        int outNumber = convertUnsigned255Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned255NumberConvert_100_expect_success() {
        int inNumber = 100;
        int outNumber = convertUnsigned255Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned255NumberConvert_255_expect_success() {
        int inNumber = 255;
        int outNumber = convertUnsigned255Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testUnsigned255NumberConvert_300_expect_failure() {
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
    public void testUnsigned255NumberConvert_minus300_expect_failure() {
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
    public void testSigned127NumberConvert_minus127_expect_success() {
        int inNumber = -127;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_minus50_expect_success() {
        int inNumber = -50;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_0_expect_success() {
        int inNumber = 0;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_50_expect_success() {
        int inNumber = 50;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_127_expect_success() {
        int inNumber = 127;
        int outNumber = convertSigned127Number(inNumber);
        assertEquals(inNumber, outNumber);
    }

    @Test
    public void testSigned127NumberConvert_minus128_expect_failure() {
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
    public void testSigned127NumberConvert_128_expect_failure() {
        int inNumber = 128;
        try {
            convertSigned127Number(inNumber);
        } catch (Exception exception) {
            assertTrue(true);
            return;
        }
        assertFalse(true);
    }

}