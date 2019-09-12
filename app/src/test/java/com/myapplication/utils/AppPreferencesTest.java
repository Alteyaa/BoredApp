package com.myapplication.utils;



import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AppPreferencesTest {

    public AppPreferences appPreferences;


    @Mock
    ISharedStorage sharedStorage;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        appPreferences = new AppPreferences(sharedStorage);

        try {
            when (sharedStorage.get(AppPreferences.PREF_IS_FIRST_LAUNCH,true))
            .thenReturn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testFirstLaunch(){
        boolean isFisrtLaunch = appPreferences.isFirstLaunch();

        assertTrue(isFisrtLaunch);


    }

}