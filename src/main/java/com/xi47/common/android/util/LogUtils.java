package com.xi47.common.android.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author HanXu
 */
@SuppressWarnings("WeakerAccess")
public final class LogUtils {

    private static final String DEFAULT_TAG = "TCX";

    private static boolean sIsDebug = true;
    private static String sTag = null;

    public static void e(Object object, String message) {
        if (!sIsDebug) {
            return;
        }
        Log.e(object.getClass().getName(), message);
    }

    public static void e(String message) {
        if (!sIsDebug) {
            return;
        }
        Log.e(TextUtils.isEmpty(sTag) ? DEFAULT_TAG : sTag, message);
    }

    public static void setTag(String tag) {
        sTag = tag;
    }

    public static void d(Object object, String message) {
        if (!sIsDebug) {
            return;
        }
        Log.d(object.getClass().getName(), message);
    }

    public static void d(String message) {
        if (!sIsDebug) {
            return;
        }
        Log.d(TextUtils.isEmpty(sTag) ? DEFAULT_TAG : sTag, message);
    }

    public static void d(String tag, String message) {
        if (!sIsDebug) {
            return;
        }
        Log.d(tag, message);
    }

    public static void setDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void v(String tag, String msg) {
        if (!sIsDebug) {
            return;
        }
        Log.v(tag, msg);
    }
}
