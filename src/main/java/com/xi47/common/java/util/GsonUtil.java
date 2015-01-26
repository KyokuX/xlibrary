package com.xi47.common.java.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by X on 15/1/26.
 */
public final class GsonUtil {

    private Map<Type, Object> mTypes = null;

    private Gson mGson = null;

    public Gson getGson() {
        return mGson;
    }

    public void appendType(Type type, Object o) {
        mTypes.put(type, o);
        for (Map.Entry entry : mTypes.entrySet()) {
            mGson = new GsonBuilder().registerTypeAdapter((Type) entry.getKey(), entry.getValue()).create();
        }
    }

    public static GsonUtil getInstance() {
        return InstanceHolder.mInstance;
    }

    private GsonUtil() {
        mTypes = new HashMap<>();
    }

    private final static class InstanceHolder {

        private static GsonUtil mInstance = new GsonUtil();
    }
}
