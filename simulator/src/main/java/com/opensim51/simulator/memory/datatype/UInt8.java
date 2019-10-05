package com.opensim51.simulator.memory.datatype;

import java.util.Objects;

public final class UInt8 implements UInt<UInt8> {

    public static final UInt8 ZERO = new UInt8(0x00);
    public static final UInt8 ONE = new UInt8(0x01);
    public static final UInt8 MAX_VALUE = new UInt8(0xff);

    public static final UInt8 MASK_HIGH_NIBBLE = new UInt8(0xf0);
    public static final UInt8 MASK_LOW_NIBBLE = new UInt8(0x0f);

    private int value;
    private boolean overflow;

    public UInt8(int value) {
        this.value = value & 0xff;
    }

    public UInt8(UInt8 value) {
        this(value.value);
    }

    @Override
    public UInt8 inc() {
        return add(ONE);
    }

    @Override
    public UInt8 add(UInt8 data) {
        int result = value + data.value;
        overflow = result > 0xff;

        return toUInt8(result);
    }

    @Override
    public UInt8 subtract(UInt8 data) {
        int result = value - data.value;
        overflow = result < 0;

        return toUInt8(result);
    }

    @Override
    public UInt8 shiftLeft(int n) {
        return toUInt8(value << n);
    }

    @Override
    public UInt8 shiftRight(int n) {
        return toUInt8(value >> n);
    }

    @Override
    public UInt8 not() {
        return toUInt8(value ^ 0xff);
    }

    @Override
    public UInt8 xor(UInt8 data) {
        return toUInt8(value ^ data.value);
    }

    @Override
    public UInt8 or(UInt8 data) {
        return toUInt8(value | data.value);
    }

    @Override
    public UInt8 and(UInt8 data) {
        return toUInt8(value & data.value);
    }

    public UInt8 setBit(int position) {
        return toUInt8(value | (0x1 << position));
    }

    public UInt8 clearBit(int position) {
        return toUInt8(value & ~(0x1 << position));
    }

    public boolean getBitValue(int position) {
        return ((value >> position) & 0x1) == 0x1;
    }

    public boolean isOverflowOccurred() {
        return overflow;
    }

    @Override
    public int toInt() {
        return value;
    }

    public UInt16 toUInt16() {
        return new UInt16(toInt());
    }

    private UInt8 toUInt8(int value) {
        return new UInt8(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UInt8 uInt8 = (UInt8) o;
        return value == uInt8.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(UInt8 o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        String hex = Integer.toHexString(value);
        return hex.length() > 2 ? hex.substring(hex.length() - 2) : hex;
    }

}
