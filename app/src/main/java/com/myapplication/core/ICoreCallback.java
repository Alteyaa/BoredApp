package com.myapplication.core;


public interface ICoreCallback<T> {
    void onSuccess(T action);

    void onFailure(Exception e);

}
