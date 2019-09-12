package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.myapplication.data.ActionRequestOptions;
import com.myapplication.data.BoredApiClient;
import com.myapplication.data.IBoredApiClient;
import com.myapplication.model.BoredAction;
import com.myapplication.model.EActionType;

import org.angmarch.views.NiceSpinner;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.action_accessibility_value)
    ProgressBar accessibilityProgress;
    @BindView(R.id.loading)
    ProgressBar progressBar_loading;
    @BindView(R.id.refresh)
    Button btnRefresh;
    @BindView(R.id.action_activity)
    TextView txtActivity;
    @BindView(R.id.action_type)
    TextView txtType;
    @BindView(R.id.action_participants)
    TextView txtParticipants;
    @BindView(R.id.action_price)
    TextView txtPrice;
    @BindView(R.id.action_like_img)
    ImageView likeImg;
    @BindView(R.id.action_like_container)
    View likeImgContainer;

    private boolean isLiked = false;
    private BoredApiClient client;

    @OnClick(R.id.refresh)
    public void onClick(View view) {
        refreshAction();
    }

    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.rangeSeekbar3)
    CrystalRangeSeekbar rangeSeekbar;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<String> dataset = new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.categories)));
        niceSpinner.attachDataSource(dataset);
        client = new BoredApiClient();
        likeImgContainer.setOnClickListener(view -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
            likeImg.startAnimation(anim);

            isLiked = !isLiked;
            if (isLiked) {
                likeImg.setImageResource(R.drawable.ic_heart_filed);
            } else {
                likeImg.setImageResource(R.drawable.ic_favorite);
            }
        });
    }


    private void showLoading() {
        progressBar_loading.setVisibility(View.INVISIBLE);
        accessibilityProgress.setVisibility(View.VISIBLE);
        txtActivity.setVisibility(View.VISIBLE);
        txtType.setVisibility(View.VISIBLE);
        txtPrice.setVisibility(View.VISIBLE);
        txtParticipants.setVisibility(View.VISIBLE);


    }

    private void hideLoading() {

        progressBar_loading.setVisibility(View.VISIBLE);
        accessibilityProgress.setVisibility(View.INVISIBLE);
        txtActivity.setVisibility(View.INVISIBLE);
        txtType.setVisibility(View.INVISIBLE);
        txtPrice.setVisibility(View.INVISIBLE);
        txtParticipants.setVisibility(View.INVISIBLE);
    }


    public void refreshAction() {

        int selected = niceSpinner.getSelectedIndex();
        String type = null;
        if (selected != 0) {
            type = EActionType.values()[selected - 1]
                    .toString()
                    .toLowerCase();
        }

        ActionRequestOptions requestOptions = new ActionRequestOptions(
                type,
                0.0f,
                1.0f,
                0f,
                1f,
                null
        );
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if (minValue == null) {
                    rangeSeekbar.getSelectedMinValue();
                } else {
                    rangeSeekbar.getSelectedMaxValue();
                }

                hideLoading();
                client.getBoredAction(requestOptions,
                        new IBoredApiClient.BoredActionCallBack() {
                            @Override
                            public void onSuccess(BoredAction action) {

                                int accessibility = (int) (action.getAccessibility() * 100);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    accessibilityProgress.setProgress(accessibility, true);
                                } else {
                                    accessibilityProgress.setProgress(accessibility);
                                }

                                txtType.setText(action.getType().toString());
                                txtActivity.setText(action.getActivity());
                                txtPrice.setText((action.getPrice()).toString());
                                txtParticipants.setText((action.getParticipants()).toString());
                                showLoading();
                                Log.d("ololo", "Receive action - " + action.getTitle() + " " + action.getActivity());

                            }

                            @Override
                            public void onFailure(Exception e) {

                                Log.d("ololo", "Failure" + e.getMessage());
                            }

                        });
            }
        });
    }

}