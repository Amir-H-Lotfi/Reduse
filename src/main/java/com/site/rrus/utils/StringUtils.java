package com.site.rrus.utils;

import static java.lang.Integer.*;

public class StringUtils {
    private StringUtils() {
    }

    public static synchronized String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toHexString(b));
        }
        return sb.toString();
    }


}
