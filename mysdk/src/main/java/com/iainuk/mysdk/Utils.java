package com.iainuk.mysdk;

import android.text.TextUtils;

import com.iainuk.mysdk.exception.InvalidInputException;

public class Utils {
    public static <T> T requireNonNull(T obj, String field) {
        if (obj == null) {
            throw new InvalidInputException(field);
        }

        return obj;
    }

    public static String requireHasLength(String string, String field) {
        if (TextUtils.isEmpty(string)) {
            throw new InvalidInputException(field);
        }

        return string;
    }

    public static String requireValidHexCode(String hexColorCode, String field) {
        if (TextUtils.isEmpty(hexColorCode) || hexColorCode.charAt(0) != '#') {
            throw new InvalidInputException(field);
        }

        return hexColorCode;
    }

    public static int requireNonNegative(int value, String field) {
        if (value < 0) {
            throw new InvalidInputException(field);
        }

        return value;
    }
}
