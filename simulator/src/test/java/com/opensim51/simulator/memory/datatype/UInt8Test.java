package com.opensim51.simulator.memory.datatype;

import org.junit.Test;

import static org.junit.Assert.*;

public class UInt8Test {

    @Test
    public void inc() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.inc(), UInt8.ONE);

        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.inc(), UInt8.ZERO);
    }

    @Test
    public void add() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.add(UInt8.ONE), UInt8.ONE);

        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.add(UInt8.ONE), UInt8.ZERO);
    }

    @Test
    public void subtract() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.sub(UInt8.ONE), UInt8.MAX_VALUE);

        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.sub(UInt8.ONE), new UInt8(0xfe));

        assertEquals(new UInt8(0xfe).sub(new UInt8(0xff)), new UInt8(0xff));
    }

    @Test
    public void shiftLeft() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.shl(0), maxValue);
        assertEquals(maxValue.shl(1), new UInt8(0xfe));
        assertEquals(maxValue.shl(7), new UInt8(0x80));
        assertEquals(maxValue.shl(8), UInt8.ZERO);
    }

    @Test
    public void shiftRight() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.shr(0), maxValue);
        assertEquals(maxValue.sar(1), new UInt8(0xff));
        assertEquals(maxValue.shr(7), UInt8.ONE);
        assertEquals(maxValue.shr(8), UInt8.ZERO);
    }

    @Test
    public void not() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.not(), UInt8.ZERO);

        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.not(), UInt8.MAX_VALUE);

        UInt8 lowNibble = UInt8.MASK_LOW_NIBBLE;
        assertEquals(lowNibble.not(), UInt8.MASK_HIGH_NIBBLE);
    }

    @Test
    public void xor() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.xor(UInt8.MAX_VALUE), UInt8.MAX_VALUE);
        assertEquals(zero.xor(UInt8.MASK_LOW_NIBBLE), UInt8.MASK_LOW_NIBBLE);

        UInt8 highNibble = UInt8.MASK_HIGH_NIBBLE;
        assertEquals(highNibble.xor(UInt8.MAX_VALUE), UInt8.MASK_LOW_NIBBLE);
        assertEquals(highNibble.xor(UInt8.MASK_LOW_NIBBLE), UInt8.MAX_VALUE);

        UInt8 one = UInt8.ONE;
        assertEquals(one.xor(UInt8.ONE), UInt8.ZERO);
        assertEquals(one.xor(UInt8.MAX_VALUE), new UInt8(0xfe));
    }

    @Test
    public void or() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.or(UInt8.MAX_VALUE), UInt8.MAX_VALUE);
        assertEquals(zero.or(UInt8.MASK_LOW_NIBBLE), UInt8.MASK_LOW_NIBBLE);

        UInt8 highNibble = UInt8.MASK_HIGH_NIBBLE;
        assertEquals(highNibble.or(UInt8.MAX_VALUE), UInt8.MAX_VALUE);
        assertEquals(highNibble.or(UInt8.MASK_LOW_NIBBLE), UInt8.MAX_VALUE);

        UInt8 one = UInt8.ONE;
        assertEquals(one.or(UInt8.ONE), UInt8.ONE);
        assertEquals(one.or(UInt8.MAX_VALUE), UInt8.MAX_VALUE);
    }

    @Test
    public void and() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.and(UInt8.MAX_VALUE), UInt8.ZERO);
        assertEquals(zero.and(UInt8.MASK_LOW_NIBBLE), UInt8.ZERO);

        UInt8 highNibble = UInt8.MASK_HIGH_NIBBLE;
        assertEquals(highNibble.and(UInt8.MAX_VALUE), UInt8.MASK_HIGH_NIBBLE);
        assertEquals(highNibble.and(UInt8.MASK_LOW_NIBBLE), UInt8.ZERO);

        UInt8 one = UInt8.ONE;
        assertEquals(one.and(UInt8.ONE), UInt8.ONE);
        assertEquals(one.and(UInt8.MAX_VALUE), UInt8.ONE);
    }

    @Test
    public void setBit() {
        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.stb(0), UInt8.ONE);
        assertEquals(zero.stb(7), new UInt8(0x80));
        assertEquals(zero.stb(8), zero);
    }

    @Test
    public void clearBit() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.clb(0), new UInt8(0xfe));
        assertEquals(maxValue.clb(7), new UInt8(0x7f));
        assertEquals(maxValue.clb(8), maxValue);
    }

    @Test
    public void getBitValue() {
        assertTrue(UInt8.ONE.bt(0));
        assertFalse(UInt8.ONE.bt(1));
        assertTrue(UInt8.MASK_LOW_NIBBLE.bt(0));
        assertTrue(UInt8.MASK_LOW_NIBBLE.bt(1));
        assertTrue(UInt8.MASK_LOW_NIBBLE.bt(2));
        assertTrue(UInt8.MASK_LOW_NIBBLE.bt(3));
        assertFalse(UInt8.MASK_LOW_NIBBLE.bt(4));
        assertFalse(UInt8.MASK_LOW_NIBBLE.bt(5));
        assertFalse(UInt8.MASK_LOW_NIBBLE.bt(6));
        assertFalse(UInt8.MASK_LOW_NIBBLE.bt(7));
        assertFalse(UInt8.MAX_VALUE.bt(8));
    }

    @Test
    public void isOverflowed() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        UInt8 zero = maxValue.inc();
        assertTrue(zero.isOverflowed());

        UInt8 one = UInt8.ONE;
        maxValue = one.add(new UInt8(0xfe));
        assertFalse(maxValue.isOverflowed());

        one = UInt8.ONE;
        zero = one.add(UInt8.MAX_VALUE);
        assertTrue(zero.isOverflowed());
    }

    @Test
    public void toUInt16() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.x16(), new UInt16(0xff));

        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.x16(), UInt16.ZERO);

        UInt8 one = UInt8.ONE;
        assertEquals(one.x16(), UInt16.ONE);
    }

    @Test
    public void equals() {
        assertEquals(UInt8.ONE, UInt8.ONE);
        assertEquals(UInt8.ONE, new UInt8(0x1));
        assertNotEquals(UInt8.ZERO, UInt8.ONE);
        assertNotEquals(UInt8.ZERO, UInt16.ZERO);
    }

    @Test
    public void compareTo() {
        assertEquals(0, UInt8.ONE.compareTo(new UInt8(0x1)));
        assertTrue(UInt8.ONE.compareTo(UInt8.ZERO) > 0);
        assertTrue(UInt8.ZERO.compareTo(UInt8.ONE) < 0);
    }

}
