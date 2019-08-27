package com.myapplication;

import android.app.Application;

import com.myapplication.utils.AppPreferences;
import com.myapplication.utils.ISharedStorage;
import com.myapplication.utils.SharedStorage;

public class App extends Application {

    public static ISharedStorage sharedStorage;
    public static AppPreferences appPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedStorage = new SharedStorage(this, "prefs");
        appPreferences = new AppPreferences(sharedStorage);


    }
}
