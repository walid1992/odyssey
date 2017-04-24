package com.syswin.toon.lib.martian.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author   : walid
 * Data     : 2016-09-18  12:57
 * Describe :
 */

public class TUtils {

    public static <T> T getEmptyConstructorT(Object o, int index) {
        Type[] params = ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments();
        String exceptionMessage;
        if (params.length <= 0) {
            return null;
        }
        try {
            return ((Class<T>) params[index]).newInstance();
        } catch (InstantiationException e) {
            exceptionMessage = e.getMessage();
        } catch (IllegalAccessException e) {
            exceptionMessage = e.getMessage();
        }
        Logger.e(exceptionMessage);
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
