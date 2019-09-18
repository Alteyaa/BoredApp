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

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.myapplication.data.ActionRequestOptions;
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
    @BindView(R.id.action_price_amount)
    TextView actionAmount;
    @BindView(R.id.action_accessibility)
    TextView actionAccessibility;
    @BindView(R.id.action_participants)
    TextView txtParticipants;
    @BindView(R.id.action_price)
    TextView txtPrice;
    @BindView(R.id.action_like_img)
    ImageView likeImg;
    @BindView(R.id.action_participants_icon)
    ImageView participantsIcon;
    @BindView(R.id.action_like_container)
    View likeImgContainer;

    private boolean isLiked = false;

    @OnClick(R.id.refresh)
    public void onClick(View view) {
        refreshAction();
    }

    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;

    @BindView(R.id.rangeSeekbarPrice)
    CrystalRangeSeekbar rangeSeekbarPrice;

    @BindView(R.id.rangeSeekbarAccessibility)
    CrystalRangeSeekbar rangeSeekbarAccessibility;

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
        likeImgContainer.setOnClickListener(view -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
            likeImg.startAnimation(anim);

            isLiked = !isLiked;
            if (isLiked) {
                likeImg.setImageResource(R.drawable.ic_heart_filed);
            } else {
                likeImg.setImageResource(R.drawable.ic_empty);
            }
        });
    }


    private void showView() {
        progressBar_loading.setVisibility(View.INVISIBLE);
        accessibilityProgress.setVisibility(View.VISIBLE);
        txtActivity.setVisibility(View.VISIBLE);
        txtType.setVisibility(View.VISIBLE);
        txtPrice.setVisibility(View.VISIBLE);
        txtParticipants.setVisibility(View.VISIBLE);
        actionAmount.setVisibility(View.VISIBLE);
        actionAccessibility.setVisibility(View.VISIBLE);
        participantsIcon.setVisibility(View.VISIBLE);


    }

    private void hideView() {
        progressBar_loading.setVisibility(View.VISIBLE);
        accessibilityProgress.setVisibility(View.INVISIBLE);
        txtActivity.setVisibility(View.INVISIBLE);
        txtType.setVisibility(View.INVISIBLE);
        txtPrice.setVisibility(View.INVISIBLE);
        txtParticipants.setVisibility(View.INVISIBLE);
        actionAmount.setVisibility(View.INVISIBLE);
        actionAccessibility.setVisibility(View.INVISIBLE);
        participantsIcon.setVisibility(View.INVISIBLE);

    }


    public void refreshAction() {
        hideView();
        int selected = niceSpinner.getSelectedIndex();
        String type = null;
        if (selected != 0) {
            type = EActionType.values()[selected - 1]
                    .toString()
                    .toLowerCase();

        }

        double minPrice = rangeSeekbarPrice.getSelectedMinValue().doubleValue() / 100.0;
        double maxPrice = rangeSeekbarPrice.getSelectedMaxValue().doubleValue() / 100.0;

        Log.d("ololo", "Min " + minPrice + " Max " + maxPrice);

        double minAccesibility = rangeSeekbarAccessibility.getSelectedMinValue().doubleValue() / 100.0;
        double maxAccesibility = rangeSeekbarAccessibility.getSelectedMaxValue().doubleValue() / 100.0;

        Log.d("ololo", "Min " + minAccesibility + " Max " + maxAccesibility);



        ActionRequestOptions requestOptions = new ActionRequestOptions(
                type,
                minPrice,
                maxPrice,
                minAccesibility,
                maxAccesibility,
                null
        );



        App.boredApiClient.getBoredAction(requestOptions,
                new IBoredApiClient.BoredActionCallBack() {
                    @Override
                    public void onSuccess(BoredAction action) {
                        showView();
                        if (action.getAccessibility() == null) {
                            Log.d("ololo", "Acce is null");
                        }
                        int accessibility = 0;
                        if (action.getAccessibility() != null) {
                            accessibility = (int) (action.getAccessibility() * 100);
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            accessibilityProgress.setProgress(accessibility, true);
                        } else {
                            accessibilityProgress.setProgress(accessibility);
                        }

                        txtType.setText(action.getType().toString());
                        txtActivity.setText(action.getActivity());
                        txtParticipants.setText((action.getParticipants()).toString());
                        actionAccessibility.setText(action.getAccessibility().toString());

                        if (action.getParticipants() == 1) {
                            participantsIcon.setImageResource(R.drawable.ic_part);
                        } else if (action.getParticipants() == 2) {
                            participantsIcon.setImageResource(R.drawable.ic_part_2);
                        } else if (action.getParticipants() == 3) {
                            participantsIcon.setImageResource(R.drawable.ic_part_3);
                        } else if (action.getParticipants() == 4) {
                            participantsIcon.setImageResource(R.drawable.ic_part_4);
                        } else participantsIcon.setImageResource(R.drawable.ic_part_4);

                        if (action.getPrice() == 0) {
                            actionAmount.setText("Free");
                        } else if (0.1>=action.getPrice()&&action.getPrice() <= 0.3) {
                            actionAmount.setText("💰");
                        } else if (0.3>action.getPrice()&&action.getPrice() <= 0.6) {
                            actionAmount.setText("💰💰");
                        } else if (0.6>action.getPrice()&&action.getPrice() <= 1) {
                            actionAmount.setText("💰💰💰");


                        }
                        Log.d("ololo", "Receive action - " + action.getTitle() + " " + action.getActivity());

                    }

                    @Override
                    public void onFailure(Exception e) {

                        Log.d("ololo", "Failure" + e.getMessage());
                    }

                });
    }

}