package simulator.memory.datatype;

import org.junit.Test;

import static org.junit.Assert.*;

public class UInt16Test {

    @Test
    public void inc() {
        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.inc(), UInt16.ONE);

        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.inc(), UInt16.ZERO);
    }

    @Test
    public void add() {
        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.add(UInt16.ONE), UInt16.ONE);

        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.add(UInt16.ONE), UInt16.ZERO);
    }

    @Test
    public void subtract() {
        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.subtract(UInt16.ONE), UInt16.MAX_VALUE);

        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.subtract(UInt16.ONE), new UInt16(0xfffe));
    }

    @Test
    public void shiftLeft() {
        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.shiftLeft(0), maxValue);
        assertEquals(maxValue.shiftLeft(1), new UInt16(0xfffe));
        assertEquals(maxValue.shiftLeft(15), new UInt16(0x8000));
        assertEquals(maxValue.shiftLeft(16), UInt16.ZERO);
    }

    @Test
    public void shiftRight() {
        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.shiftRight(0), maxValue);
        assertEquals(maxValue.shiftRight(1), new UInt16(0x7fff));
        assertEquals(maxValue.shiftRight(15), UInt16.ONE);
        assertEquals(maxValue.shiftRight(16), UInt16.ZERO);
    }

    @Test
    public void not() {
        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.not(), UInt16.ZERO);

        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.not(), UInt16.MAX_VALUE);

        UInt16 lowByte = UInt16.MASK_LOW_BYTE;
        assertEquals(lowByte.not(), UInt16.MASK_HIGH_BYTE);
    }

    @Test
    public void xor() {
        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.xor(UInt16.MAX_VALUE), UInt16.MAX_VALUE);
        assertEquals(zero.xor(UInt16.MASK_LOW_BYTE), UInt16.MASK_LOW_BYTE);

        UInt16 highByte = UInt16.MASK_HIGH_BYTE;
        assertEquals(highByte.xor(UInt16.MAX_VALUE), UInt16.MASK_LOW_BYTE);
        assertEquals(highByte.xor(UInt16.MASK_LOW_BYTE), UInt16.MAX_VALUE);

        UInt16 one = UInt16.ONE;
        assertEquals(one.xor(UInt16.ONE), UInt16.ZERO);
        assertEquals(one.xor(UInt16.MAX_VALUE), new UInt16(0xfffe));
    }

    @Test
    public void or() {
        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.or(UInt16.MAX_VALUE), UInt16.MAX_VALUE);
        assertEquals(zero.or(UInt16.MASK_LOW_BYTE), UInt16.MASK_LOW_BYTE);

        UInt16 highByte = UInt16.MASK_HIGH_BYTE;
        assertEquals(highByte.or(UInt16.MAX_VALUE), UInt16.MAX_VALUE);
        assertEquals(highByte.or(UInt16.MASK_LOW_BYTE), UInt16.MAX_VALUE);

        UInt16 one = UInt16.ONE;
        assertEquals(one.or(UInt16.ONE), UInt16.ONE);
        assertEquals(one.or(UInt16.MAX_VALUE), UInt16.MAX_VALUE);
    }

    @Test
    public void and() {
        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.and(UInt16.MAX_VALUE), UInt16.ZERO);
        assertEquals(zero.and(UInt16.MASK_LOW_BYTE), UInt16.ZERO);

        UInt16 highByte = UInt16.MASK_HIGH_BYTE;
        assertEquals(highByte.and(UInt16.MAX_VALUE), UInt16.MASK_HIGH_BYTE);
        assertEquals(highByte.and(UInt16.MASK_LOW_BYTE), UInt16.ZERO);

        UInt16 one = UInt16.ONE;
        assertEquals(one.and(UInt16.ONE), UInt16.ONE);
        assertEquals(one.and(UInt16.MAX_VALUE), UInt16.ONE);
    }

    @Test
    public void toUInt8() {
        UInt16 maxValue = UInt16.MAX_VALUE;
        assertEquals(maxValue.toUInt8(), UInt8.MAX_VALUE);

        UInt16 zero = UInt16.ZERO;
        assertEquals(zero.toUInt8(), UInt8.ZERO);
    }

    @Test
    public void equals() {
        assertEquals(UInt16.ONE, UInt16.ONE);
        assertEquals(UInt16.ONE, new UInt16(0x1));
        assertNotEquals(UInt16.ZERO, UInt16.ONE);
        assertNotEquals(UInt16.ZERO, UInt8.ZERO);
    }

    @Test
    public void compareTo() {
        assertEquals(0, UInt16.ONE.compareTo(new UInt16(0x1)));
        assertTrue(UInt16.ONE.compareTo(UInt16.ZERO) > 0);
        assertTrue(UInt16.ZERO.compareTo(UInt16.ONE) < 0);
    }

}