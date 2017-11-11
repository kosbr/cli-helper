package com.github.kosbr.cli.util;

public final class CliUtil {

    public static String[] removeFirst(final String[] original) {
        if (original.length == 1) {
            return new String[0];
        }
        final String[] result = new String[original.length - 1];
        for (int i = 1; i < original.length; i++) {
            result[i - 1] = original[i];
        }
        return result;
    }
}
