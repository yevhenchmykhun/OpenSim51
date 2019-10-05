package com.opensim51.gui.util;

import org.apache.commons.lang3.StringUtils;

public final class IntegerUtil {

    public static final String REGEX_HEX_OCT_DEC_INTEGER = "(0[xX][0-9a-fA-F]+|0[0-7]+|(0|[1-9]\\d*))";

    private IntegerUtil() {
    }

    public static boolean isValid(String value) {
        return StringUtils.isNotBlank(value) && value.matches(REGEX_HEX_OCT_DEC_INTEGER);
    }

    public static int parseInt(String value) {
        if (StringUtils.isBlank(value) || !value.matches(REGEX_HEX_OCT_DEC_INTEGER)) {
            throw new IllegalArgumentException("unable to parse input");
        }

        if (value.startsWith("0x") || value.startsWith("0X")) {
            return Integer.parseInt(value.substring(2), 16);
        } else if (value.startsWith("0") && value.length() > 1) {
            return Integer.parseInt(value.substring(1), 8);
        } else {
            return Integer.parseInt(value);
        }
    }

    public static String toString(int value, int radix, int leadingZeros) {
        String result;
        switch (radix) {
            case 16:
                result = String.format("%0" + leadingZeros + "x", value);
                break;
            case 8:
                result = String.format("%0" + leadingZeros + "o", value);
                break;
            case 10:
                result = String.format("%0" + leadingZeros + "i", value);
                break;
            default:
                throw new IllegalArgumentException("unknown radix");
        }

        return result;
    }

    public static String toStringWithPrefix(int value, int radix, int leadingZeros) {
        String result = toString(value, radix, leadingZeros);
        switch (radix) {
            case 16:
                result = "0x" + result;
                break;
            case 8:
                result = "0" + result;
                break;
            default:
                throw new IllegalArgumentException("unknown radix");
        }

        return result;
    }

}
