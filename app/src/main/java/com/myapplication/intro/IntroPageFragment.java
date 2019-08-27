package com.myapplication.intro;


import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroPageFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = view.findViewById(R.id.mTextView);
        tv.setText(getArguments().getInt(ARG_TITLE_RES_ID));

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(getArguments().getInt(ARG_IMAGE_RES_ID));

    }


}
