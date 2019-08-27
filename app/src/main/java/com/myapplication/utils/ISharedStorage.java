package com.myapplication.utils;

public interface ISharedStorage {

    <T> void set(String key, T value) throws Exception;

    <T> T get(String key, T defValue) throws Exception;

    boolean contains(String key);

    void remove(String key);

    void clear();
}


