package com.myapplication.data;

import com.myapplication.core.ICoreCallback;
import com.myapplication.model.BoredAction;
public interface IBoredApiClient {

    void getBoredAction(

           ActionRequestOptions eActionRequest,
           BoredActionCallBack callBack
    );

    interface BoredActionCallBack extends ICoreCallback<BoredAction> {

    }

}
