package com.myapplication;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.intro.IntroActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        redirect();
    }

    private void redirect() {
        if (App.appPreferences.isFirstLaunch()) {
            App.appPreferences.setLaunched();
            IntroActivity.start(this);
        } else {
            MainActivity.start(this);
        }

        finish();
    }

}
