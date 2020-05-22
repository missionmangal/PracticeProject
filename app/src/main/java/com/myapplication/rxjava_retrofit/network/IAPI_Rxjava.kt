package com.myapplication.rxjava_retrofit.network

import com.data.*
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import org.reactivestreams.Publisher
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IAPI_Rxjava {

//    val URL_BASE = "https://guessthebeach.herokuapp.com/api/"

    @GET("employees")
     fun getEmployee():Deferred<Response<EmployeeData>>

    @GET("employees")
     fun getAllEmployeesRxJava(): Observable<Response<EmployeeData>>
    @GET("employee/{id}")
     fun getEmployeeRxJava(@Path("id")id:String): Observable<Response<Data>>


    @PUT("update/{id}")
    fun updateEmployee(@Path("id")id:String,@Body body: RequestBody):Observable<Response<Data>>

    @GET("employees")
     fun getPosts():Deferred<Response<EmployeeData>>

    @GET("posts/")
    fun getPost():Observable<List<PostDataModel>>
    @GET("posts/{id}/comments/")
    fun getComments(@Path("id") id:Int ):Observable<List<CommentsDataModel>>


    @GET("search")
    fun searchApi(@Query("q") search:String,@Query("per_page")per_page:Int):Observable<TreeModel>

}