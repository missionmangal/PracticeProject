package com.myapplication.Network;

import com.data.Topics;

import java.util.List;

import retrofit2.http.GET;

public interface IApi2 {
//    String URL_BASE = "https://guessthebeach.herokuapp.com/api/";
    String URL_BASE_COROUTINE = "http://dummy.restapiexample.com/api/v1/";
    String URL_BASE_SEARCH = "https://demo.dataverse.org/api/";

    @GET("topics/")
    List<Topics> getTopicsRx();
}