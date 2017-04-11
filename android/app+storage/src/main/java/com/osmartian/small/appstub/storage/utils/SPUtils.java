package com.osmartian.small.appstub.storage.utils;

import android.content.Context;
import android.content.SharedPreferences;

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

    private static SharedPreferences getSP(Context ctx) {
        Preconditions.checkNotNull(ctx, "context is empty !!!");
        return ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    // save 操作 start
    public static void put(Context ctx, String key, Long value) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putLong(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context ctx, String key, Integer value) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putInt(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context ctx, String key, String value) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context ctx, String key, Float value) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putFloat(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static void put(Context ctx, String key, Boolean value) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply(editor);
    }


    // get 操作 satrt
    public static String getString(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        return getSP(ctx).getString(key, "");
    }

    public static int getInt(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        return getSP(ctx).getInt(key, 0);
    }

    public static float getFloat(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        return getSP(ctx).getFloat(key, 0);
    }

    public static boolean getBoolean(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        return getSP(ctx).getBoolean(key, false);
    }

    public static long getLong(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        return getSP(ctx).getLong(key, 0);
    }
    // get 操作 end

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context ctx) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(Context ctx, String key) {
        Preconditions.checkNotNull(key, "key is empty !!!");
        return getSP(ctx).contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll(Context ctx) {
        return getSP(ctx).getAll();
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
