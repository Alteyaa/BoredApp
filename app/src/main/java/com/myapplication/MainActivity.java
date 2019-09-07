package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myapplication.data.BoredApiClient;
import com.myapplication.data.IBoredApiClient;
import com.myapplication.model.BoredAction;

import org.angmarch.views.NiceSpinner;


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

    @OnClick(R.id.refresh)
    public void onClick(View view) {
        refreshAction();
    }

    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }



    private void showLoading() {
        progressBar_loading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar_loading.setVisibility(View.GONE);
    }



    public void refreshAction() {
        showLoading();
        new BoredApiClient().getBoredAction(new IBoredApiClient.BoredActionCallBack() {
            @Override
            public void onSuccess(BoredAction action) {
                hideLoading();

                int accessibility = (int) (action.getAccessibility() * 100);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    accessibilityProgress.setProgress(accessibility, true);
                } else {
                    accessibilityProgress.setProgress(accessibility);
                }
                txtActivity.setText(action.getActivity());
                txtPrice.setText((action.getPrice()).toString());
                txtParticipants.setText((action.getParticipants()).toString());

                Log.d("ololo", "Receive action - " + action.getTitle() + " " + action.getActivity());

            }

            @Override
            public void onFailure(Exception e) {

                Log.d("ololo", "Failure" + e.getMessage());
            }

        });

    }


}