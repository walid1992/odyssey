package com.syswin.toon.lib.martian.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

import java.lang.reflect.Type;

/**
 * Author   : walid
 * Data     : 2016-08-22  13:46
 * Describe : Gson Utils
 */

public class JsonUtils {

    private static final Gson GSON = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            Object object = GSON.fromJson(json, (Type) classOfT);
            return Primitives.wrap(classOfT).cast(object);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }

}
