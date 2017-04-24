package com.syswin.toon.lib.martian.utils;

import android.content.Context;
import android.widget.Toast;

import com.syswin.toon.lib.martian.app.MartianApp;

/**
 * Author   : walid
 * Data     : 2016-08-29  16:04
 * Describe :
 */
public class ToastUtils {

    public static void show(int resId) {
        Context context = MartianApp.instance();
        show(context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        Context context = MartianApp.instance();
        show(context.getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(int resId, Object... args) {
        Context context = MartianApp.instance();
        show(String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration, Object... args) {
        Context context = MartianApp.instance();
        show(String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }

    public static void show(CharSequence text, int duration) {
        Context context = MartianApp.instance();
        Toast.makeText(context, text, duration).show();
    }

}
