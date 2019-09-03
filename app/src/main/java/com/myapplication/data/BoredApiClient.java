package com.myapplication.data;

import com.myapplication.model.BoredAction;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class BoredApiClient implements IBoredApiClient {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BoredNetworkClient client = retrofit.create(BoredNetworkClient.class);

    @Override
    public void getBoredAction(BoredActionCallBack callBack) {

        Call<BoredAction> call = client.getBoredAction();

        call.enqueue(new CoreCallBack<BoredAction>() {
            @Override
            void onSuccess(BoredAction result) {
                callBack.onSuccess(result);
            }

            @Override
            void onFailure(Exception e) {
                callBack.onFailure(e);

            }

        });


    }

    interface BoredNetworkClient {

        @GET("api/activity/")
        Call<BoredAction> getBoredAction(


        ); //TODO: Add query parameters

    }

}