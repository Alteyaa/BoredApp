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
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    PageIndicatorView pageIndicatorView;
    private IntroPagerAdapter mIntroAdapter;
    private ViewPager mViewPager;
    private TextView mSkipBtn, mNextBtn;
    ArrayList<IntroPageConfig> pages = new ArrayList<>();

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
        initViewPager();
        mNextBtn = findViewById(R.id.next_btn);
        mNextBtn.setOnClickListener(this);
        mSkipBtn = findViewById(R.id.skip_btn);
        mSkipBtn.setOnClickListener(this);

    }

    private void initViewPager() {

        pages.add(new IntroPageConfig(
                R.string.title_activity_intro_page_2,
                R.drawable.photoshoot2));

        pages.add(new IntroPageConfig(
                R.string.title_activity_intro_page_3,
                R.drawable.photoshoot3));

        pages.add(new IntroPageConfig(
                R.string.title_activity_intro_page_1,
                R.drawable.photoshoot4));

        mIntroAdapter = new IntroPagerAdapter(getSupportFragmentManager(), pages);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mIntroAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int i) {
                onPageChanged(i);
                onPageSkip(i);
            }
        });
        pageIndicatorView.setViewPager(mViewPager);
    }

    private void onPageSkip(int position) {
        String btnFinish = "Skip";
        if (position == 2) {
            btnFinish = "";
        }
        mSkipBtn.setText(btnFinish);

    }

    private void onPageChanged(int position) {
        String btnNext = "Next";
        if (position == 2) {
            btnNext = "Finish";
        }
        mNextBtn.setText(btnNext);
    }


    private void onNextClick() {
        if (mViewPager.getCurrentItem() == mIntroAdapter.getCount() - 1) {
            MainActivity.start(this);
            finish();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn:
                onNextClick();
                break;
            case R.id.skip_btn:
                MainActivity.start(this);
                finish();
        }
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {

        List<IntroPageConfig> pages;

        IntroPagerAdapter(FragmentManager fm,
                List<IntroPageConfig> pages
        ) {
            super(fm);
            this.pages = pages;
        }

        @Override
        public Fragment getItem(int position) {
            return IntroPageFragment.newInstance(pages.get(position));
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
