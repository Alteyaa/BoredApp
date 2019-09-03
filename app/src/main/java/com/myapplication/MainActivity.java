package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myapplication.data.BoredApiClient;
import com.myapplication.data.IBoredApiClient;
import com.myapplication.model.BoredAction;

public class MainActivity extends AppCompatActivity {

    private Button btnRefresh;
    TextView txtActivity;
    TextView txtAccessibility;
    TextView txtType;
    TextView txtParticipants;
    TextView txtPrice;
    private ProgressBar progressBar;



    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }


    private void init() {
        btnRefresh = findViewById(R.id.refresh);
        txtActivity = findViewById(R.id.textAction);
        txtAccessibility = findViewById(R.id.textAccessibility);
        txtType = findViewById(R.id.textType);
        txtParticipants = findViewById(R.id.textParticipants);
        txtPrice = findViewById(R.id.textPrice);
        progressBar = (ProgressBar)findViewById(R.id.loading);

    }


    private void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideLoading(){
        progressBar.setVisibility(View.GONE);
    }



    public void refreshAction() {
        showLoading();
        new BoredApiClient().getBoredAction(new IBoredApiClient.BoredActionCallBack() {
            @Override
            public void onSuccess(BoredAction action) {
                hideLoading();
                txtActivity.setText(action.getActivity());
                txtType.setText(action.getType());
                txtAccessibility.setText((action.getAccessibility()).toString());
                txtPrice.setText((action.getPrice()).toString());
                txtParticipants.setText((action.getParticipants()).toString());
                Log.d("ololo", "Receive action - " + action.getTitle() + " " + action.getActivity());

            }

            @Override
            public void onFailure(Exception e) {

                Log.d("ololo","Failure" + e.getMessage());
            }

        });

    }

    public void onClick(View view) {
        refreshAction();
    }
}