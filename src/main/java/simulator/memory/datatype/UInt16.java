package simulator.memory.datatype;

import java.math.BigInteger;
import java.util.Objects;

public final class UInt16 implements Comparable<UInt16> {

    public static final UInt16 ZERO = new UInt16(0x0000);
    public static final UInt16 ONE = new UInt16(0x0001);
    public static final UInt16 MAX_VALUE = new UInt16(0xffff);

    private BigInteger value;

    public UInt16(int magnitude) {
        this(1, magnitude);
    }

    public UInt16(UInt16 data) {
        this(data.value.signum(), data.value.intValue());
    }

    private UInt16(int sign, int magnitude) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((magnitude & 0xff00L) >> 8);
        bytes[1] = (byte) ((magnitude & 0xffL));
        value = new BigInteger(sign, bytes);
    }

    public int toInt() {
        return value.intValue() & 0x0000ffff;
    }

    public UInt16 inc() {
        return add(ONE);
    }

    public UInt16 add(UInt16 data) {
        return toUInt16(data.value.add(value));
    }

    public UInt16 subtract(UInt16 data) {
        return toUInt16(value.subtract(data.value));
    }

    public UInt16 shiftLeft(int n) {
        return toUInt16(value.shiftLeft(n));
    }

    public UInt16 shiftRight(int n) {
        return toUInt16(value.shiftRight(n));
    }

    public UInt16 not() {
        return toUInt16(value.not());
    }

    public UInt16 xor(UInt16 data) {
        return toUInt16(value.xor(data.value));
    }

    public UInt16 or(UInt16 data) {
        return toUInt16(value.or(data.value));
    }

    public UInt16 and(UInt16 data) {
        return toUInt16(value.and(data.value));
    }

    public UInt8 toUInt8() {
        return new UInt8(toInt());
    }

    private UInt16 toUInt16(BigInteger src) {
        return new UInt16(1, src.abs().intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UInt16 that = (UInt16) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(UInt16 o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(value.intValue());
        return hex.length() > 4 ? hex.substring(hex.length() - 4) : hex;
    }

}
