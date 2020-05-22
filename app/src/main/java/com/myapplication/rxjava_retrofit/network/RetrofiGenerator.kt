package com.myapplication.rxjava_retrofit.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.myapplication.Network.IApi
import com.myapplication.Network.IApi2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofiGenerator {

    companion object{

        fun getClient():OkHttpClient{
            var logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            return  OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

        }
        var retrofitBuilder = Retrofit.Builder()
//                .baseUrl(IApi2.URL_BASE)
//                .baseUrl(IApi2.URL_BASE_COROUTINE)
                .baseUrl(IApi2.URL_BASE_SEARCH)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
        private var retrofit = retrofitBuilder.build();
        private var requestApi = retrofit.create(IAPI_Rxjava::class.java)

        fun getRequestApi():IAPI_Rxjava{
            return requestApi
        }
    }
}