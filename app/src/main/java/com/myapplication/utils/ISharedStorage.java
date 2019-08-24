package com.myapplication.utils;

public interface ISharedStorage {

    void setBoolean(String key, boolean value);

    boolean getBoolean(String key, boolean defValue);

    boolean contains(String key);

    void remove(String key);

    void clear();

}


