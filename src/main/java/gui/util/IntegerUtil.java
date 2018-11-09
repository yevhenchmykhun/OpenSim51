package gui.util;

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
            return -1;
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
        switch (radix) {
            case 16:
                return String.format("%0" + leadingZeros + "x", value);
            case 8:
                return String.format("%0" + leadingZeros + "o", value);
            case 10:
                return String.format("%0" + leadingZeros + "i", value);
        }

        return "";
    }

}
