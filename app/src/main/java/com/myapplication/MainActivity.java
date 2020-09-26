package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.data.Contact;
import com.data.Topics;
import com.myapplication.Network.BaseResponse;
import com.myapplication.Network.IApi;
import com.myapplication.Network.NetworkCall;
import com.myapplication.Network.ServiceCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        callApi();
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                callApiSuccess();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                callApiFailed();
            }
        });



    }




//    Testing retry callling with api
    public void callApiSuccess(){

        NetworkCall networkCall = new NetworkCall();
        ServiceCallBack serviceCallBack = new ServiceCallBack() {
            @Override
            public void onSuccess(int tag, Response<BaseResponse> baseResponse) {
                Log.d("aaaaaaaa",baseResponse.message());
                Toast.makeText(MainActivity.this,"Api Call success ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(Throwable t, int tag, String msg) {
                Log.d("bbbbbbbbbbb",msg);
                count++;
                Toast.makeText(MainActivity.this,"Api Call Tried count "+ count, Toast.LENGTH_LONG).show();
            }
        };
        networkCall.setServiceCallBack(serviceCallBack);
        networkCall.setRequestTag(1001);

        Call<List<Topics>>call= networkCall.getRetrofit(true,true).getTopicsRetry();
//        Call<Topics>call= networkCall.getRetrofit(true,true).getTopicsRetryWrong();
        call.enqueue(networkCall.requestCallback());

    }

    public void callApiFailed(){

        NetworkCall networkCall = new NetworkCall();
        ServiceCallBack serviceCallBack = new ServiceCallBack() {
            @Override
            public void onSuccess(int tag, Response<BaseResponse> baseResponse) {
                Log.d("aaaaaaaa",baseResponse.message());
                Toast.makeText(MainActivity.this,"Api Call success ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(Throwable t, int tag, String msg) {
                Log.d("bbbbbbbbbbb",msg);
                count++;
                Toast.makeText(MainActivity.this,"Api Call Tried count "+ count, Toast.LENGTH_LONG).show();
            }
        };
        networkCall.setServiceCallBack(serviceCallBack);
        networkCall.setRequestTag(1001);

//        Call<List<Topics>>call= networkCall.getRetrofit(true,true).getTopicsRetry();
        IApi api = networkCall.getRetrofit(true,true);
        Call<Topics>call= api.getTopicsRetryWrong();
        call.enqueue(networkCall.requestCallback());

    }



}
