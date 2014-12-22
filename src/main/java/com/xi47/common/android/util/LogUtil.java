package com.xi47.common.android.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author HanXu
 */
public class LogUtil {

    private static final String DEFAULT_TAG = "TCX";

    private static boolean sIsDebug = true;
    private static String sTag = null;

    public static final void e(Object object, String message) {
        if (!sIsDebug) {
            return;
        }
        Log.e(object.getClass().getName(), message);
    }

    public static final void e(String message) {
        if (!sIsDebug) {
            return;
        }
        Log.e(TextUtils.isEmpty(sTag) ? DEFAULT_TAG : sTag, message);
    }

    public static final void setTag(String tag) {
        sTag = tag;
    }

    public static final void d(Object object, String message) {
        if (!sIsDebug) {
            return;
        }
        Log.d(object.getClass().getName(), message);
    }

    public static final void d(String message) {
        if (!sIsDebug) {
            return;
        }
        Log.d(TextUtils.isEmpty(sTag) ? DEFAULT_TAG : sTag, message);
    }

    public static final void d(String tag, String message) {
        if (!sIsDebug) {
            return;
        }
        Log.d(tag, message);
    }

    public static final void setDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void v(String tag, String msg) {
        if (!sIsDebug) {
            return;
        }
        Log.v(tag, msg);
    }
}
