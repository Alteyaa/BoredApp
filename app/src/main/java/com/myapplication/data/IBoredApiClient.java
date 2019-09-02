package com.myapplication.data;

import com.myapplication.model.BoredAction;

public interface IBoredApiClient {

    void getBoredAction(

  final BoredActionCallBack callBack

    );
    interface BoredActionCallBack{


        void onSuccess(BoredAction action);
        void onFailure(Exception e);
    }

}
