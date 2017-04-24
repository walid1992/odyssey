package com.syswin.toon.lib.martian.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.syswin.toon.lib.martian.app.MartianApp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author   : walid
 * Data     : 2016-09-04  20:08
 * Describe : 共享存储工具类
 */

public class SPUtils {

    private static final String FILE_NAME = "rock_data";

    private static SharedPreferences getSP() {
        return MartianApp.instance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    // save 操作 start
    public static void put(@StringRes int keyId, Long value) {
        if (value == null) {
            return;
        }
        SharedPreferences.Editor editor = getSP().edit();
        editor.putLong(ResUtils.getString(keyId), value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(@StringRes int keyId, Integer value) {
        if (value == null) {
            return;
        }
        SharedPreferences.Editor editor = getSP().edit();
        editor.putInt(ResUtils.getString(keyId), value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(@StringRes int keyId, String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        SharedPreferences.Editor editor = getSP().edit();
        editor.putString(ResUtils.getString(keyId), value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(@StringRes int keyId, Float value) {
        if (value == null) {
            return;
        }
        SharedPreferences.Editor editor = getSP().edit();
        editor.putFloat(ResUtils.getString(keyId), value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(@StringRes int keyId, Boolean value) {
        if (value == null) {
            return;
        }
        SharedPreferences.Editor editor = getSP().edit();
        editor.putBoolean(ResUtils.getString(keyId), value);
        SharedPreferencesCompat.apply(editor);
    }
    // save 操作 end


    // get 操作 satrt
    public static String getString(@StringRes int keyid) {
        return getSP().getString(ResUtils.getString(keyid), "");
    }

    public static int getInt(@StringRes int keyid) {
        return getSP().getInt(ResUtils.getString(keyid), 0);
    }

    public static float getFloat(@StringRes int keyid) {
        return getSP().getFloat(ResUtils.getString(keyid), 0);
    }

    public static boolean getBoolean(@StringRes int keyid) {
        return getSP().getBoolean(ResUtils.getString(keyid), false);
    }

    public static long getLong(@StringRes int keyid) {
        return getSP().getLong(ResUtils.getString(keyid), 0);
    }
    // get 操作 end

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(@StringRes int keyId) {
        SharedPreferences.Editor editor = getSP().edit();
        editor.remove(ResUtils.getString(keyId));
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getSP().edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences.Editor editor = getSP().edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(Context context, String key) {
        return getSP().contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll(Context context) {
        return getSP().getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {

        private static final Method sApplyMethod = findApplyMethod();

        // 反射查找apply的方法
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 如果找到则使用apply执行，否则使用commit
        static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

}
