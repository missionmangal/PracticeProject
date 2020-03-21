package com.myapplication.Network;

import android.app.ProgressDialog;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myapplication.RetryApi.RetryCallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by PrP on 26-Oct-16.
 */
public class NetworkCall {
    //    private final Context context;
    private ProgressDialog progressDialog;
    private int requestTag;
    private ServiceCallBack serviceCallBack;

    private final Callback<BaseResponse> callback = new Callback<BaseResponse>() {

        @Override
        public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
//            Util.showLog(response.toString());//response.errorBody().source().readByteString()
            if (response != null && response.isSuccessful()) {
                if (response.code() == 500) {
                    serviceCallBack.onFail(new Throwable(), 500, response.body() != null ? response.body().getMessage() : "");
                    return;
                }
                if (response.code() == 404) {
                    serviceCallBack.onFail(new Throwable(), requestTag, response.body() != null ? response.body().getMessage() : "");
                    return;
                } /*else if (response.body() != null && response.body().getCode() != null && response.body().getCode().equalsIgnoreCase("401")) {
//                Force Logout
                    serviceCallBack.onFail(new Throwable(), 401, response.body() != null ? response.body().getMessage() : "");
                    return;
                }*/
                serviceCallBack.onSuccess(requestTag, response);
            } else {
                if (response.code() == 500) {
                    serviceCallBack.onFail(new Throwable(), 500, response.body() != null ? response.body().getMessage() : "");
                    return;
                }
                serviceCallBack.onFail(new Throwable(), requestTag, response.body() != null ? response.body().getMessage() : "");
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
//            Util.showLog(t.getMessage());
            Log.d("bbbbbbbbbbb",t.getMessage());
            serviceCallBack.onFail(new Throwable(), requestTag, "");
        }
    };

    /**
     * Gets service call back.
     *
     * @return the service call back
     */
    public ServiceCallBack getServiceCallBack() {
        return serviceCallBack;
    }

    /**
     * Sets service call back.
     *
     * @param serviceCallBack the service call back
     */
    public void setServiceCallBack(ServiceCallBack serviceCallBack) {
        this.serviceCallBack = serviceCallBack;
    }

    /**
     * Gets request tag.
     *
     * @return the request tag
     */
    public int getRequestTag() {
        return requestTag;
    }

    /**
     * Request callback callback.
     *
     * @return the callback
     */
    public Callback requestCallback() {
        return callback;
    }

    /**
     * Sets request tag.
     *
     * @param requestType the request type
     */
    public void setRequestTag(int requestType) {
        this.requestTag = requestType;
    }

    /**
     * Instantiates a new Network call.
     */
    public NetworkCall() {
//        this.context = context;
    }

    /**
     * Gets retrofit.
     *
     * @param isShowLoading the is show loading
     * @param pass          the pass
     * @return the retrofit
     */
    public IApi getRetrofit(boolean isShowLoading, final boolean pass) {

//        if (Util.isOnline()) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder();
                    builder.addHeader("Content-Type", "application/json");
                    builder.addHeader("Accept", "application/json");
                    builder.addHeader("Version-Code", "1");
                    builder.addHeader("Device-Type", "android");



                    Request request = builder.method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            httpClient.addInterceptor(logging);  // <-- this is the important line!


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(IApi.BASE_URL)
                    .client(httpClient.build())
                    .addCallAdapterFactory(RetryCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            if (isShowLoading) {

            }
            // prepare call in Retrofit 2.0

            IApi api = retrofit.create(IApi.class);
            return api;
        /*} else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    serviceCallBack.onFail(new Throwable("Please check internet connection"), -111, "");
                }
            }, 500);

            return null;
        }*/
    }


}