package com.opensim51.simulator.memory.datatype;

import java.util.Objects;

public final class UInt16 implements UInt<UInt16> {

    public static final UInt16 ZERO = new UInt16(0x0000);
    public static final UInt16 ONE = new UInt16(0x0001);
    public static final UInt16 MAX_VALUE = new UInt16(0xffff);

    public static final UInt16 MASK_HIGH_BYTE = new UInt16(0xff00);
    public static final UInt16 MASK_LOW_BYTE = new UInt16(0x00ff);

    private static final UInt16[] cache = new UInt16[256];

    static {
        cache[0] = ZERO;
        cache[1] = ONE;
        cache[255] = MASK_LOW_BYTE;

        for (int i = 2; i < 255; i++) {
            cache[i] = new UInt16(i);
        }
    }

    private final int value;

    private UInt16(int value) {
        this.value = value & 0xffff;
    }

    public static UInt16 valueOf(int value) {
        return (value & 0xffff) <= 0x00ff ? cache[value & 0x00ff] : new UInt16(value);
    }

    @Override
    public UInt16 inc() {
        return valueOf(value + 1);
    }

    @Override
    public UInt16 add(UInt16 data) {
        return valueOf(value + data.value);
    }

    @Override
    public UInt16 sub(UInt16 data) {
        return valueOf(value - data.value);
    }

    @Override
    public UInt16 shl(int n) {
        return valueOf(value << n);
    }

    @Override
    public UInt16 shr(int n) {
        return valueOf(value >> n);
    }

    @Override
    public UInt16 not() {
        return valueOf(value ^ 0xffff);
    }

    @Override
    public UInt16 xor(UInt16 data) {
        return valueOf(value ^ data.value);
    }

    @Override
    public UInt16 or(UInt16 data) {
        return valueOf(value | data.value);
    }

    @Override
    public UInt16 and(UInt16 data) {
        return valueOf(value & data.value);
    }

    @Override
    public int toInt() {
        return value;
    }

    public UInt8 toUInt8() {
        return UInt8.valueOf(value);
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
        return Integer.toHexString(value);
    }

}
