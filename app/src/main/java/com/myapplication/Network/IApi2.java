package com.myapplication.Network;

import com.data.Topics;

import java.util.List;

import retrofit2.http.GET;

public interface IApi2 {
    String URL_BASE = "https://guessthebeach.herokuapp.com/api/";

    @GET("topics/")
    List<Topics> getTopicsRx();
}