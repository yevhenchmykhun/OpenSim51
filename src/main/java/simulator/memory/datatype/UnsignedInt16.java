package simulator.memory.datatype;

import java.math.BigInteger;
import java.util.Objects;

public final class UnsignedInt16 implements Comparable<UnsignedInt16> {

    private BigInteger value;

    public UnsignedInt16(int magnitude) {
        this(1, magnitude);
    }

    public UnsignedInt16(UnsignedInt16 unsignedInt16) {
        this(unsignedInt16.value.signum(), unsignedInt16.value.intValue());
    }

    private UnsignedInt16(int sign, int magnitude) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((magnitude & 0xff00L) >> 8);
        bytes[1] = (byte) ((magnitude & 0xffL));
        value = new BigInteger(sign, bytes);
    }

    public int toInt() {
        return value.intValue() & 0x0000ffff;
    }

    public UnsignedInt16 inc() {
        return add(new UnsignedInt16(1));
    }

    public UnsignedInt16 add(UnsignedInt16 data) {
        return toUnsignedInt16(data.value.add(value));
    }

    public UnsignedInt16 subtract(UnsignedInt16 data) {
        return toUnsignedInt16(value.subtract(data.value));
    }

    public UnsignedInt16 shiftLeft(int n) {
        return toUnsignedInt16(value.shiftLeft(n));
    }

    public UnsignedInt16 shiftRight(int n) {
        return toUnsignedInt16(value.shiftRight(n));
    }

    public UnsignedInt16 not() {
        return toUnsignedInt16(value.not());
    }

    public UnsignedInt16 xor(UnsignedInt16 data) {
        return toUnsignedInt16(value.xor(data.value));
    }

    public UnsignedInt16 or(UnsignedInt16 data) {
        return toUnsignedInt16(value.or(data.value));
    }

    public UnsignedInt16 and(UnsignedInt16 data) {
        return toUnsignedInt16(value.and(data.value));
    }

    private UnsignedInt16 toUnsignedInt16(BigInteger src) {
        return new UnsignedInt16(1, src.abs().intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnsignedInt16 that = (UnsignedInt16) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(UnsignedInt16 o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(value.intValue());
        return hex.length() > 4 ? hex.substring(hex.length() - 4) : hex;
    }

}
