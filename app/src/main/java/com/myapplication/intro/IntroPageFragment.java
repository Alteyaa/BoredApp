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

import com.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroPageFragment extends Fragment {

    private final static String ARG_TITLE_RES_ID = "title_res";
    private final static String ARG_IMAGE_RES_ID = "image_res";


    public IntroPageFragment() {
        // Required empty public constructor
    }

    public static IntroPageFragment newInstance(@StringRes int titleRes,
                                                @DrawableRes int imageRes) {
        IntroPageFragment fragment = new IntroPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_RES_ID, titleRes);
        args.putInt(ARG_IMAGE_RES_ID, imageRes);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_intro_page, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int imageRes = getArguments().getInt(ARG_IMAGE_RES_ID);
        int stringRes = getArguments().getInt(ARG_TITLE_RES_ID);
    }

}
