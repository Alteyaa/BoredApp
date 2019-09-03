package com.myapplication.data;

import com.myapplication.core.CoreApiClient;
import com.myapplication.core.ResponseHandler;
import com.myapplication.model.BoredAction;

import retrofit2.Call;
import retrofit2.http.GET;


public class BoredApiClient extends CoreApiClient implements IBoredApiClient {


    BoredNetworkClient client = getRetrofit("http://www.boredapi.com/")
            .create(BoredNetworkClient.class);

    @Override
    public void getBoredAction(BoredActionCallBack callBack) {

        Call<BoredAction> call = client.getBoredAction();

        call.enqueue(new ResponseHandler<BoredAction>() {
            @Override
             public void onSuccess(BoredAction result) {
                callBack.onSuccess(result);
            }

            @Override
           public void onFailure(Exception e) {
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