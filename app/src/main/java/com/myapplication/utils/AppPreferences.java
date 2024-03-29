package com.myapplication.utils;

public class AppPreferences {

    public static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";
    public static final String PREF_IS_LIGHT_MODE = "is_light_mode";


    private ISharedStorage sharedStorage;

    public AppPreferences(ISharedStorage sharedStorage) {
        this.sharedStorage = sharedStorage;
    }

    public boolean isFirstLaunch() {
        try {
            return sharedStorage.get(PREF_IS_FIRST_LAUNCH, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void setLaunched() {
        try {
            sharedStorage.set(PREF_IS_FIRST_LAUNCH, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
