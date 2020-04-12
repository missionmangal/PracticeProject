package com.myapplication.rxjava_retrofit.network

import com.data.CommentsDataModel
import com.data.PostDataModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface IAPI_Rxjava {

    companion object{
        val base_api ="http://jsonplaceholder.typicode.com/";
    }

    @GET("posts/")
    fun getPost():Observable<List<PostDataModel>>
    @GET("posts/{id}/comments/")
    fun getComments(@Path("id") id:Int ):Observable<List<CommentsDataModel>>
}