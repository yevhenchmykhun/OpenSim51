package com.opensim51.simulator.memory.datatype;

import java.util.Objects;

public final class UInt16 implements UInt<UInt16> {

    public static final UInt16 ZERO = new UInt16(0x0000);
    public static final UInt16 ONE = new UInt16(0x0001);
    public static final UInt16 MAX_VALUE = new UInt16(0xffff);

    public static final UInt16 MASK_HIGH_BYTE = new UInt16(0xff00);
    public static final UInt16 MASK_LOW_BYTE = new UInt16(0x00ff);

    private int value;

    public UInt16(int value) {
        this.value = value & 0xffff;
    }

    public UInt16(UInt16 value) {
        this(value.value);
    }

    public UInt16 inc() {
        return add(ONE);
    }

    public UInt16 add(UInt16 data) {
        return toUInt16(data.value + value);
    }

    public UInt16 sub(UInt16 data) {
        return toUInt16(value - data.value);
    }

    public UInt16 shl(int n) {
        return toUInt16(value << n);
    }

    public UInt16 shr(int n) {
        return toUInt16(value >> n);
    }

    public UInt16 not() {
        return toUInt16(value ^ 0xffff);
    }

    public UInt16 xor(UInt16 data) {
        return toUInt16(value ^ data.value);
    }

    public UInt16 or(UInt16 data) {
        return toUInt16(value | data.value);
    }

    public UInt16 and(UInt16 data) {
        return toUInt16(value & data.value);
    }

    public int toInt() {
        return value;
    }

    public UInt8 toUInt8() {
        return UInt8.valueOf(toInt());
    }

    private UInt16 toUInt16(int value) {
        return new UInt16(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UInt16 uInt16 = (UInt16) o;
        return value == uInt16.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(UInt16 o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(value);
        return hex.length() > 4 ? hex.substring(hex.length() - 4) : hex;
    }

}
