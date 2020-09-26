package com.myapplication.NetworkTest;

import android.util.Log;

import com.data.Topics;
import com.google.gson.Gson;
import com.myapplication.Network.BaseResponse;
import com.myapplication.Network.IApi;
import com.myapplication.Network.NetworkCall;
import com.myapplication.Network.RemoteDataSource;
import com.myapplication.Network.ServiceCallBack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class RemoteDataSourceTest {

    List<Topics> mResultList;
    MockWebServer mMockWebserver;
    NetworkCall networkCall ;
    ServiceCallBack serviceCallBack;
//    TestSubscriber<List<Topics>> mSubscriber;

    @Before
    public void setUp(){
        Topics topics = new Topics(1,"Discern The Beach");
        Topics topicsTwo = new Topics(1,"Discern The Football Player");
        mResultList = new ArrayList();
        mResultList.add(topics);
        mResultList.add(topicsTwo);
        networkCall = new NetworkCall();
        mMockWebserver = new MockWebServer();

//        mSubscriber = new TestSubscriber<>();
    }


    @Test
    public void serverCallWithError(){
//        Given
        String url = "dfdf/";
        mMockWebserver.enqueue(new MockResponse().setBody(new Gson().toJson(mResultList)));
        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebserver.url(url))
                .build();
        RemoteDataSource remoteDataSource = new RemoteDataSource(retrofit);
//        remoteDataSource.getTopicsRx();//.subscribe(mSubscriber);
//        mSubscriber.assertNoErrors();
//        mSubscriber.assertCompleted();
    }



    @Test
    public void serverCallSuccessful(){
        String url = "https://guessthebeach.herokuapp.com/api/";
        mMockWebserver.enqueue(new MockResponse().setBody(new Gson().toJson(mResultList)));
        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebserver.url(url))
                .build();
        RemoteDataSource remoteDataSource = new RemoteDataSource(retrofit);
//        remoteDataSource.getTopicsRx();//.subscribe(mSubscriber);
//        mSubscriber.assertNoErrors();
//        mSubscriber.assertCompleted();
    }


    @Test
    public void serverSuccess(){
        serviceCallBack = new ServiceCallBack() {
            @Override
            public void onSuccess(int tag, Response<BaseResponse> baseResponse) {
                Log.println(1,"aaaaaaaaaaaaa",baseResponse.message());
            }

            @Override
            public void onFail(Throwable t, int tag, String msg) {
                Log.println(1,"bbbbbbbbbbbbb",msg);
            }
        };
        networkCall.setServiceCallBack(serviceCallBack);
        networkCall.setRequestTag(IApi.TOPIC_TAG);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IApi api = retrofit.create(IApi.class);
        Call<BaseResponse<Topics>> responceCall = api.getTopicsRx();
        responceCall.enqueue(networkCall.requestCallback());
    }
}
