package com.myapplication.Network;


import com.data.Topics;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RemoteDataSource  {

    private IApi2 api;

    public RemoteDataSource(Retrofit retrofit) {


        this.api = retrofit.create(IApi2.class);
    }


    public void getTopicsRx(ServiceCallBack serviceCallBack, NetworkCall networkCall) {
        try {
            networkCall.setServiceCallBack(serviceCallBack);
            networkCall.setRequestTag(IApi.TOPIC_TAG);
            Call<BaseResponse<Topics>> responceCall = networkCall.getRetrofit(true, true).getTopicsRx();
            responceCall.enqueue(networkCall.requestCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return api.getTopicsRx();
    }


}
