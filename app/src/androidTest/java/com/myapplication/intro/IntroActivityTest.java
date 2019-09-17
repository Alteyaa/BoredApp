package com.myapplication.intro;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class IntroActivityTest {


    @Rule
    public ActivityTestRule<IntroActivity> activityTestRule=
            new ActivityTestRule<>(
                    IntroActivity.class,
                    true,
                    false
            );

    @Before
    public void setUp()  {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
    }

    @Test
    public void textNextButtonClick(){

    }
    @Test
    public void testMainActivityLaunchOnIntro(){

    }
}