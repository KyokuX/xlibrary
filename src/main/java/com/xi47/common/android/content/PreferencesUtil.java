package com.xi47.common.android.content;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HanXu
 */
public class PreferencesUtil {

    private Map<String, SharedPreferences> mPreferences = null;

    private PreferencesUtil() {
        mPreferences = new HashMap<String, SharedPreferences>();
    }

    public static final PreferencesUtil getInstance() {
        return InstanceHolder.mInstance;
    }

    public void initPreference(Context context, String name) {
        if (mPreferences.containsKey(name)) {
            return;
        }
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        mPreferences.put(name, preferences);
    }

    public boolean put(String name, String key, boolean value) {
        SharedPreferences preferences = mPreferences.get(name);
        if (preferences == null) {
            return false;
        }
        return preferences.edit().putBoolean(key, value).commit();
    }

    public boolean get(String name, String key, boolean defaultValue) {
        SharedPreferences preferences = mPreferences.get(name);
        if (preferences == null) {
            return defaultValue;
        }
        return preferences.getBoolean(key, defaultValue);
    }

    public int get(String name, String key, int defaultValue) {
        SharedPreferences preferences = mPreferences.get(name);
        if (preferences == null) {
            return defaultValue;
        }
        return preferences.getInt(key, defaultValue);
    }

    private static class InstanceHolder {
        private static final PreferencesUtil mInstance = new PreferencesUtil();
    }
}
