package simulator.memory.datatype;

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
        assertEquals(zero.subtract(UInt8.ONE), UInt8.MAX_VALUE);

        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.subtract(UInt8.ONE), new UInt8(0xfe));
    }

    @Test
    public void shiftLeft() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.shiftLeft(0), maxValue);
        assertEquals(maxValue.shiftLeft(1), new UInt8(0xfe));
        assertEquals(maxValue.shiftLeft(7), new UInt8(0x80));
        assertEquals(maxValue.shiftLeft(8), UInt8.ZERO);
    }

    @Test
    public void shiftRight() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.shiftRight(0), maxValue);
        assertEquals(maxValue.shiftRight(1), new UInt8(0x7f));
        assertEquals(maxValue.shiftRight(7), UInt8.ONE);
        assertEquals(maxValue.shiftRight(8), UInt8.ZERO);
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
        assertEquals(zero.setBit(0), UInt8.ONE);
        assertEquals(zero.setBit(7), new UInt8(0x80));
        assertEquals(zero.setBit(8), zero);
    }

    @Test
    public void clearBit() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.clearBit(0), new UInt8(0xfe));
        assertEquals(maxValue.clearBit(7), new UInt8(0x7f));
        assertEquals(maxValue.clearBit(8), maxValue);
    }

    @Test
    public void getBitValue() {
        assertTrue(UInt8.ONE.getBitValue(0));
        assertFalse(UInt8.ONE.getBitValue(1));
        assertFalse(UInt8.MAX_VALUE.getBitValue(8));
    }

    @Test
    public void isOverflowOccurred() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        UInt8 zero = maxValue.inc();
        assertTrue(maxValue.isOverflowOccurred());

        UInt8 one = UInt8.ONE;
        maxValue = one.add(new UInt8(0xfe));
        assertFalse(one.isOverflowOccurred());

        one = UInt8.ONE;
        zero = one.add(UInt8.MAX_VALUE);
        assertTrue(one.isOverflowOccurred());
    }

    @Test
    public void toUInt16() {
        UInt8 maxValue = UInt8.MAX_VALUE;
        assertEquals(maxValue.toUInt16(), new UInt16(0xff));

        UInt8 zero = UInt8.ZERO;
        assertEquals(zero.toUInt16(), UInt16.ZERO);
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
