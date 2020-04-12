package com.myapplication.rxjava_retrofit.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofiGenerator {

    companion object{
        var retrofitBuilder = Retrofit.Builder()
                .baseUrl(IAPI_Rxjava.base_api)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        private var retrofit = retrofitBuilder.build();
        private var requestApi = retrofit.create(IAPI_Rxjava::class.java)

        fun getRequestApi():IAPI_Rxjava{
            return requestApi
        }
    }
}