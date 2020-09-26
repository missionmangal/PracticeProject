package com.myapplication.NetworkTest

import com.data.Topics
import com.google.gson.Gson
import com.myapplication.Network.BaseResponse
import com.myapplication.Network.IApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class ApiTest {

    private lateinit var mockWebServer : MockWebServer
    private lateinit var apiService:IApi

    lateinit var mResultList: ArrayList<Topics>

    lateinit var baseResponse: BaseResponse<ArrayList<Topics>>
    @Before
    fun setup(){
        val url = "https://guessthebeach.herokuapp.com/api/"
        val topics = Topics(1, "Discern The Beach")
        val topicsTwo = Topics(2, "Discern The Football Player")
        mResultList = ArrayList<Topics>()
        mResultList.add(topics)
        mResultList.add(topicsTwo)
        baseResponse = BaseResponse()
        baseResponse.data = mResultList
        baseResponse.message ="Api successfully called"
        baseResponse.isSuccess = true
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService = Retrofit.Builder()
                .baseUrl(mockWebServer.url(url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS) // For testing purposes
                        .readTimeout(10, TimeUnit.SECONDS) // For testing purposes
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .build())
                .build().create(IApi::class.java)

    }

    @After
    fun shutDownServer(){
        mockWebServer.shutdown()
    }

    @Test
    fun testApi(){

        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)

                .setBody(Gson().toJson(baseResponse))

        mockWebServer.enqueue(response)
//        var apiResponse = apiService.topicsTest2

        val responceCall: Call<List<Topics>> = apiService.getTopicsTest1()
        responceCall.enqueue(object :Callback<List<Topics>>{
            override fun onFailure(call: Call<List<Topics>>, t: Throwable) {
                System.out.println( "vishnu "+t.message)
            }

            override fun onResponse(call: Call<List<Topics>>, response: Response<List<Topics>>) {
                System.out.println(response.message())
                System.out.println(response.body()?.forEach {
                    System.out.println(it.name)
                })
                Assert.assertTrue(response.isSuccessful)

            }


        })
        Thread.sleep(5000L)
//        call.enqueue()
//        var apiResponse =  call.execute()
//        Assert.assertTrue(apiResponse.isSuccess)
//        Log.d("Result::",apiResponse.message)
    }
}