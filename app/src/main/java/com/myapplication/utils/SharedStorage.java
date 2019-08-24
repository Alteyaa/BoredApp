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
    public void setBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);

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
