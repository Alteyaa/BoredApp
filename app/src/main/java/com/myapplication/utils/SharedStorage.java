package com.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedStorage implements ISharedStorage {

    private SharedPreferences preferences;


    public SharedStorage(Context context, String preferenceName) {
        preferences = context.getSharedPreferences
                (preferenceName,
                        Context.MODE_PRIVATE
                );
    }

    @Override
    public <T> void set(String key, T value) throws Exception {
        if (value instanceof Integer) {
            preferences.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Long) {
            preferences.edit().putLong(key, (Long) value).apply();
        } else if (value instanceof String) {
            preferences.edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            preferences.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Float) {
            preferences.edit().putFloat(key, (Float) value).apply();

        } else {
            throw new Exception("Can't put preferences for" + value.getClass().getSimpleName());
        }
    }


    @Override
    public <T> T get(String key, T defValue) throws Exception {
        if (defValue instanceof Integer) {
            return (T) Integer.valueOf(preferences.getInt(key, (Integer) defValue));
        } else if (defValue instanceof Long) {
            return (T) Long.valueOf(preferences.getLong(key, (Long) defValue));
        } else if (defValue instanceof String) {
            return (T) String.valueOf(preferences.getString(key, (String) defValue));
        } else if (defValue instanceof Boolean) {
            return (T) Boolean.valueOf(preferences.getBoolean(key, (Boolean) defValue));
        } else if (defValue instanceof Float) {
            return (T) Float.valueOf(preferences.getFloat(key, (Float) defValue));
        } else {
            throw new Exception("Can't put preferences for" + defValue.getClass().getSimpleName());
        }
    }

    @Override
    public boolean contains(String key) {
        return preferences.contains(key);
    }

    @Override
    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    @Override
    public void clear() {
        preferences.edit().clear().apply();
    }
}
