package com.myapplication.intro;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.core.CoreFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public  class IntroPageFragment extends CoreFragment {

    private final static String ARG_TITLE_RES_ID = "title_res";
    private final static String ARG_IMAGE_RES_ID = "image_res";


    public IntroPageFragment() {

    }
    public static IntroPageFragment newInstance(IntroPageConfig config) {
        IntroPageFragment fragment = new IntroPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_RES_ID, config.getTitleRes());
        args.putInt(ARG_IMAGE_RES_ID, config.getImageRes());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_intro_page;
    }

    @Override
    protected void initView(View view) {
        TextView tv = view.findViewById(R.id.mTextView);
        tv.setText(getArguments().getInt(ARG_TITLE_RES_ID));
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(getArguments().getInt(ARG_IMAGE_RES_ID));

    }
}
