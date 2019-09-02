package com.myapplication.data;

import com.myapplication.model.BoredAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class BoredApiClient implements IBoredApiClient{

        private Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.boredapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

     BoredNetworkClient client = retrofit.create(BoredNetworkClient.class);

    @Override
    public void getBoredAction(BoredActionCallBack callBack) {

        Call<BoredAction> call = client.getBoredAction();
        call.enqueue(new Callback<BoredAction>() {
            @Override
            public void onResponse(Call<BoredAction> call, Response<BoredAction> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                       callBack.onSuccess(response.body());
                    } else {
                        callBack.onFailure(new Exception("Body is null"));
                    }
                }else {
                    callBack.onFailure(new Exception("Response code " + response.code()));

            }

        }

            @Override
            public void onFailure(Call<BoredAction> call, Throwable t) {
                callBack.onFailure(new Exception(t));

            }
        });
    }

}

interface BoredNetworkClient {

    @GET("api/activity/")
    Call<BoredAction> getBoredAction(


    ); //TODO: Add query parameters

}

