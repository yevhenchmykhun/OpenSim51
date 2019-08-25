package simulator.memory.datatype;

import java.math.BigInteger;
import java.util.Objects;

public final class UInt8 implements Comparable<UInt8> {

    public static final UInt8 ZERO = new UInt8(0x00);
    public static final UInt8 ONE = new UInt8(0x01);
    public static final UInt8 MAX_VALUE = new UInt8(0xff);

    public static final UInt8 MASK_HIGH_NIBBLE = new UInt8(0xf0);
    public static final UInt8 MASK_LOW_NIBBLE = new UInt8(0x0f);

    private BigInteger value;
    private boolean overflow;

    public UInt8(int magnitude) {
        this(1, magnitude);
    }

    public UInt8(UInt8 data) {
        this(data.value.signum(), data.value.byteValue());
    }

    private UInt8(int sign, int magnitude) {
        value = new BigInteger(sign, new byte[]{(byte) magnitude});
    }

    public int toInt() {
        return value.intValue() & 0x000000ff;
    }

    public UInt8 inc() {
        return add(ONE);
    }

    public UInt8 setBit(int position) {
        return toUInt8(value.setBit(position));
    }

    public UInt8 clearBit(int position) {
        return toUInt8(value.clearBit(position));
    }

    public UInt8 add(UInt8 data) {
        BigInteger result = value.add(data.value);
        overflow = result.compareTo(BigInteger.valueOf(MAX_VALUE.toInt())) > 0;

        return toUInt8(result);
    }

    public UInt8 subtract(UInt8 data) {
        BigInteger result = value.subtract(data.value);
        overflow = result.compareTo(BigInteger.valueOf(ZERO.toInt())) < 0;

        return toUInt8(result);
    }

    public UInt8 shiftLeft(int n) {
        return toUInt8(value.shiftLeft(n));
    }

    public UInt8 shiftRight(int n) {
        return toUInt8(value.shiftRight(n));
    }

    public UInt8 not() {
        return this.xor(MAX_VALUE);
    }

    public UInt8 xor(UInt8 data) {
        return toUInt8(value.xor(data.value));
    }

    public UInt8 or(UInt8 data) {
        return toUInt8(value.or(data.value));
    }

    public UInt8 and(UInt8 data) {
        return toUInt8(value.and(data.value));
    }

    public boolean isOverflowOccurred() {
        return overflow;
    }

    public UInt16 toUInt16() {
        return new UInt16(toInt());
    }

    public boolean getBitValue(int position) {
        return value.testBit(position);
    }

    private UInt8 toUInt8(BigInteger src) {
        return new UInt8(src.abs().byteValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UInt8 that = (UInt8) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(UInt8 o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(value.byteValue());
        return hex.length() > 2 ? hex.substring(hex.length() - 2) : hex;
    }
}
