package com.syswin.toon.lib.martian.utils.rxjava.rxdialog;

/**
 * Author   : walid
 * Data     : 2016-09-26  10:31
 * Describe : 判断builder 先决条件
 */

public final class Preconditions {
    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
