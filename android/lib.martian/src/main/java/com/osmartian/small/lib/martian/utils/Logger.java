package com.osmartian.small.lib.martian.utils;

import android.util.Log;

import com.osmartian.small.lib.martian.BuildConfig;

/**
 * Author   : walid
 * Data     : 2016-10-19  16:37
 * Describe : log 日志
 */
public class Logger {

    private static final String TAG = "martian";

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, buildMessage(msg));
        }
    }

    public static void d(Object obj) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, buildMessage(String.valueOf(obj)));
        }
    }

    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, buildMessage(msg));
        }
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, buildMessage(msg));
        }
    }

    public static void w(String msg) {
        Log.w(TAG, buildMessage(msg));
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            String errorMsg = buildMessage(msg);
            Log.e(TAG, errorMsg);
        }
    }

    private static String buildMessage(String msg) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        return "### " + caller.toString() + msg;
    }

}
