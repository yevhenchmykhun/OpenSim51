package simulator.memory.datatype;

import java.math.BigInteger;
import java.util.Objects;

public final class UnsignedInt8 implements Comparable<UnsignedInt8> {

    public static final UnsignedInt8 ZERO = new UnsignedInt8(0x00);
    public static final UnsignedInt8 ONE = new UnsignedInt8(0x01);
    public static final UnsignedInt8 MAX_VALUE = new UnsignedInt8(0xff);

    public static final UnsignedInt8 MASK_HIGH_NIBBLE = new UnsignedInt8(0xf0);
    public static final UnsignedInt8 MASK_LOW_NIBBLE = new UnsignedInt8(0x0f);

    private BigInteger value;
    private boolean overflow;

    public UnsignedInt8(int magnitude) {
        this(1, magnitude);
    }

    public UnsignedInt8(UnsignedInt8 data) {
        this(data.value.signum(), data.value.byteValue());
    }

    private UnsignedInt8(int sign, int magnitude) {
        value = new BigInteger(sign, new byte[]{(byte) magnitude});
    }

    public int toInt() {
        return value.intValue() & 0x000000ff;
    }

    public UnsignedInt8 inc() {
        return add(ONE);
    }

    public UnsignedInt8 setBit(int position) {
        return toUnsignedInt8(value.setBit(position));
    }

    public UnsignedInt8 clearBit(int position) {
        return toUnsignedInt8(value.clearBit(position));
    }

    public UnsignedInt8 add(UnsignedInt8 data) {
        BigInteger result = value.add(data.value);
        overflow = result.compareTo(BigInteger.valueOf(MAX_VALUE.toInt())) > 0;

        return toUnsignedInt8(result);
    }

    public UnsignedInt8 subtract(UnsignedInt8 data) {
        BigInteger result = value.subtract(data.value);
        overflow = result.compareTo(BigInteger.valueOf(ZERO.toInt())) < 0;

        return toUnsignedInt8(result);
    }

    public UnsignedInt8 shiftLeft(int n) {
        return toUnsignedInt8(value.shiftLeft(n));
    }

    public UnsignedInt8 shiftRight(int n) {
        return toUnsignedInt8(value.shiftRight(n));
    }

    public UnsignedInt8 not() {
        return toUnsignedInt8(value.not());
    }

    public UnsignedInt8 xor(UnsignedInt8 data) {
        return toUnsignedInt8(value.xor(data.value));
    }

    public UnsignedInt8 or(UnsignedInt8 data) {
        return toUnsignedInt8(value.or(data.value));
    }

    public UnsignedInt8 and(UnsignedInt8 data) {
        return toUnsignedInt8(value.and(data.value));
    }

    public boolean isOverflowOccurred() {
        return overflow;
    }

    public UnsignedInt16 toUnsignedInt16() {
        return new UnsignedInt16(toInt());
    }

    private UnsignedInt8 toUnsignedInt8(BigInteger src) {
        return new UnsignedInt8(src.abs().byteValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnsignedInt8 that = (UnsignedInt8) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(UnsignedInt8 o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(value.byteValue());
        return hex.length() > 2 ? hex.substring(hex.length() - 2) : hex;
    }
}
