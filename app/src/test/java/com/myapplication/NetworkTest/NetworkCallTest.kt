package com.myapplication.NetworkTest

import com.data.Topics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapplication.Network.BaseResponse
import com.myapplication.Network.IApi
import com.myapplication.Network.NetworkCall
import com.myapplication.Network.ServiceCallBack
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(MockitoJUnitRunner::class)
public class  NetworkCallTest {

    lateinit var networkcall:NetworkCall
    lateinit var serviceCall:ServiceCallBack

    @Before
    fun setUp(){
        networkcall = NetworkCall()
        serviceCall = object :ServiceCallBack{
            override fun onSuccess(tag: Int, baseResponse: Response<BaseResponse<Any>>?) {
                print(baseResponse?.message())
            }

            override fun onFail(t: Throwable?, tag: Int, msg: String?) {
                print(msg)
            }
        }
    }

    @Test
    fun apiTest(){
        print("msg")
        networkcall.serviceCallBack = serviceCall
        networkcall.requestTag = IApi.TOPIC_TAG
        var gson =  GsonBuilder()
                .setLenient()
                .create();
        var retrofit = Retrofit.Builder()
                .baseUrl(IApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        var iapi = retrofit.create(IApi::class.java )
        val responceCall: Call<BaseResponse<Topics>> = iapi.getTopicsRx()
        responceCall.enqueue(networkcall.requestCallback() as Callback<BaseResponse<Topics>>?)
    }
}