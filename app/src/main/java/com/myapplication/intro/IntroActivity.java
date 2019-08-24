package com.myapplication.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TextView;

import com.myapplication.MainActivity;
import com.myapplication.R;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private IntroPagerAdapter mIntroAdapter;
    private ViewPager mViewPager;
    private TextView mSkipBtn;

    public static void start(Context context) {
        Intent intent = new Intent(context, IntroActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();

    }

    private void init() {
        mSkipBtn = findViewById(R.id.skip_btn);
        mSkipBtn.setOnClickListener(this);
        initViewPager();
    }

    private void initViewPager() {
        mIntroAdapter = new IntroPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mIntroAdapter);

    }

    @Override
    public void onClick(View view) {
        MainActivity.start(this);
        finish();
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {
        public IntroPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int titleRes = R.string.app_name;
            int imageRes = R.drawable.photoshoot4;
            return IntroPageFragment.newInstance(titleRes, imageRes);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
