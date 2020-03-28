package com.opensim51.simulator.memory.datatype;

import java.util.Objects;

public final class UInt8 implements UInt<UInt8> {

    public static final UInt8 ZERO = new UInt8(0x00);
    public static final UInt8 ONE = new UInt8(0x01);
    public static final UInt8 MAX_VALUE = new UInt8(0xff);

    public static final UInt8 MASK_HIGH_NIBBLE = new UInt8(0xf0);
    public static final UInt8 MASK_LOW_NIBBLE = new UInt8(0x0f);

    private static final UInt8[] cache = new UInt8[256];

    static {
        cache[0] = ZERO;
        cache[1] = ONE;
        cache[255] = MAX_VALUE;

        for (int i = 2; i < 255; i++) {
            switch (i) {
                case 15:
                    cache[i] = MASK_LOW_NIBBLE;
                    break;
                case 240:
                    cache[i] = MASK_HIGH_NIBBLE;
                    break;
                default:
                    cache[i] = new UInt8(i);
            }
        }
    }

    private final int value;

    private UInt8(int value) {
        this.value = value & 0xff;
    }

    public static UInt8 valueOf(int value) {
        return cache[value & 0xff];
    }

    @Override
    public UInt8 inc() {
        return valueOf(value + 1);
    }

    @Override
    public UInt8 add(UInt8 data) {
        return valueOf(value + data.value);
    }

    @Override
    public UInt8 sub(UInt8 data) {
        return valueOf(value - data.value);
    }

    @Override
    public UInt8 shl(int n) {
        return valueOf(value << n);
    }

    @Override
    public UInt8 shr(int n) {
        return valueOf(value >> n);
    }

    public UInt8 sar(int n) {
        return valueOf((value & 0x80) == 0x80 ? (value >> n) | 0x80 : value >> n);
    }

    @Override
    public UInt8 and(UInt8 data) {
        return valueOf(value & data.value);
    }

    @Override
    public UInt8 or(UInt8 data) {
        return valueOf(value | data.value);
    }

    @Override
    public UInt8 xor(UInt8 data) {
        return valueOf(value ^ data.value);
    }

    @Override
    public UInt8 not() {
        return valueOf(value ^ 0xff);
    }

    public UInt8 stb(int position) {
        return valueOf(value | (0x1 << position));
    }

    public UInt8 clb(int position) {
        return valueOf(value & ~(0x1 << position));
    }

    public boolean bt(int position) {
        return ((value >> position) & 0x1) == 0x1;
    }

    public UInt16 x16() {
        return new UInt16(toInt());
    }

    public UInt16 sx16() {
        int i = toInt();
        return new UInt16((i & 0x80) == 0x80 ? i | 0xff00 : i);
    }

    public boolean lt(UInt8 value) {
        return compareTo(value) < 0;
    }

    public boolean gt(UInt8 value) {
        return compareTo(value) > 0;
    }

    public boolean le(UInt8 value) {
        return compareTo(value) <= 0;
    }

    public boolean ge(UInt8 value) {
        return compareTo(value) >= 0;
    }

    public boolean isPositiveOverflowable(UInt8 data) {
        return value + data.value > 0xff;
    }

    public boolean isNegativeOverflowable(UInt8 data) {
        return value - data.value < 0;
    }

    @Override
    public int toInt() {
        return value & 0xff;
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
        return Integer.toHexString(value);
    }

}
