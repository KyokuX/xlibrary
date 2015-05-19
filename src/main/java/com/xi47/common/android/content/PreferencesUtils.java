package com.xi47.common.android.content;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HanXu
 */
public final class PreferencesUtils {

    private Map<String, SharedPreferences> mPreferences = null;

    private PreferencesUtils() {
        //noinspection Convert2Diamond
        mPreferences = new HashMap<String, SharedPreferences>();
    }

    public static PreferencesUtils getInstance() {
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
        return preferences != null && preferences.edit().putBoolean(key, value).commit();
    }

    public boolean put(String name, String key, int value) {
        SharedPreferences preferences = mPreferences.get(name);
        return preferences != null && preferences.edit().putInt(key, value).commit();
    }

    public boolean put(String name, String key, String value) {
        SharedPreferences preferences = mPreferences.get(name);
        return preferences != null && preferences.edit().putString(key, value).commit();
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

    public String get(String name, String key, String defaultValue) {
        SharedPreferences preferences = mPreferences.get(name);
        if (preferences == null) {
            return defaultValue;
        }
        return preferences.getString(key, defaultValue);
    }

    private static class InstanceHolder {
        private static final PreferencesUtils mInstance = new PreferencesUtils();
    }
}
