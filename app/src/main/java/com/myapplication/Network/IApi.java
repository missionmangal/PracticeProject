package com.myapplication.Network;


import com.data.Topics;
import com.myapplication.RetryApi.Retry;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

;


/**
 * Created by PrP on 25-Oct-16.
 */
public interface IApi {

    String BASE_URL = "https://guessthebeach.herokuapp.com/api/";
//    String BASE_URL = "https://guessthebeach.herokuapp.com/api/ads/";
    int TOPIC_TAG = 101;

/*    @POST("challenges/deletePostChallenge")
    Call<BaseResponse> callDeletePostChallenge(@Body RequestBody body);

    @POST("stories/deleteStatus")
    Call<BaseResponse> callDeleteStoryStatus(@Body RequestBody body);

    @POST("stories/addVisitor")
    Call<BaseResponse> callSeenStoryStatus(@Body RequestBody body);*/

    @GET("topics/")
    Call<BaseResponse<Topics>> getTopicsRx();

    @GET("topics/")
    Call<List<Topics>> getTopicsTest1();
    @GET("topics/")
    Single<BaseResponse<Topics>> getTopicsTest2();

    @Retry
    @GET("topics/")
    Call<List<Topics>> getTopicsRetry();
    @Retry
    @GET("topics/")
    Call<Topics> getTopicsRetryWrong();
}