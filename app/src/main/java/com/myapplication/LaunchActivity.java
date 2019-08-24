package com.myapplication;
import android.content.Intent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.intro.IntroActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if (App.appPreferences.isFirstLaunch()) {
            startActivity(new Intent(this, IntroActivity.class));
        } else {
            MainActivity.start(this);
        }

        App.appPreferences.setLaunched();
        finish();
    }

}
