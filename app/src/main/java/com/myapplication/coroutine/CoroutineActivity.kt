package com.myapplication.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.myapplication.R
import com.myapplication.databinding.ActivityCoroutineBinding
import com.myapplication.rxjava_retrofit.network.RetrofiGenerator
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineActivity : AppCompatActivity() {

    private val TAGS: String = "CoroutineActivity"
    lateinit var mBinding: ActivityCoroutineBinding
    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)
        Log.d("Coroutine thread:: ", Thread.currentThread().name.toString())
        mBinding.btnStart.setOnClickListener {
            startCouroutine()
//            callApi()
        }

    }


//    Coroutine Job// cancel // join START

    fun fib(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1
        return fib(n - 1) + fib(n - 2)
    }

    fun jobTest() {
        var job = GlobalScope.launch(Dispatchers.Default) {
            for (i in 30..40) {
                if (isActive) {
                    var num = fib(i)
                    Log.d(TAGS, "fibonacci of $i = $num")
                } else {
                    Log.d(TAGS, "fibonacci job canceled $i")
                }
            }
            Log.d(TAGS, "End coroutine... ")
        }
        GlobalScope.launch {
            job.join()
//            job.cancel()
            Log.d(TAGS, "Job Canceled... ")
        }

    }

//    Coroutine Job// cancel // join END

    /***************** ASYNC--AWAIT START****************/
//Async Using join start
    fun testAsyncUsingJoin() {
        GlobalScope.launch(Dispatchers.IO) {
            var time = measureTimeMillis {
                var answer1: String? = null
                var answer2: String? = null
                var job1 = launch { answer1 = doNetworkCall1() }
                var job2 = launch { answer2 = doNetworkCall2() }
                job1.join()
                job2.join()
                Log.d(TAGS, "Answer from First :: $answer1")
                Log.d(TAGS, "Answer from Second :: $answer2")
            }
            Log.d(TAGS, "It took :: $time ms")
        }
    }

//Async Using join End

    //Async Using async and await Start
    fun testAsyncUsingAsyncAndAwait() {
        GlobalScope.launch {
            var time = measureTimeMillis {
                var answer1 = async { doNetworkCall1() }
                var answer2 = async { doNetworkCall2() }

                Log.d(TAGS, "Answer from First :: ${answer1.await()}")
                Log.d(TAGS, "Answer from Second :: ${answer2.await()}")
            }
            Log.d(TAGS, "It took :: $time ms")
        }
    }
//Async Using async and await End
    /***************** ASYNC--AWAIT END****************/
    private fun coroutineContextDemo() {

        GlobalScope.launch(Dispatchers.IO) {
            Log.d("Coroutine thread:: ", Thread.currentThread().name.toString())
            var text = doNetworkCall1()
            mBinding?.let { it.tvText.text = text }

        }
    }

    private fun runBlockingCoroutine() {
        count += 1
        runBlocking {
            delay(5000L)
            mBinding?.let { it.tvCount.setText("Count is $count") }
        }
        mBinding?.let { it.tvText.setText("After Text $count") }
    }

    private suspend fun delayfunction() {
        delay(5000L)
        mBinding?.let { it.tvCount.setText("Count is $count") }
    }

    private suspend fun doNetworkCall1(): String {
        count += 1
        delay(3000L)
        return "This is answer $count"
    }

    private suspend fun doNetworkCall2(): String {
        count += 1
        delay(3000L)
        return "This is answer $count"
    }

    private fun startCouroutine() {

//        for (i in 1..1000)
        var job2:Job?= null
        var job1= GlobalScope.launch {
            delay(100L)
            count += 1
            mBinding.tvCount.setText("" + count)
            for(i in 1..10) {
                println("Job 1 ::" + i)
                if(i==5){
                    job2?.let { it.join() }
                }
            }
//            if (count <= 10)
//                startCouroutine()
        }

        job2= GlobalScope.launch {
            delay(100L)
            job1.join()

            count += 1
            mBinding.tvCount.setText("" + count)
            for(i in 1..10)
                println("Job 2 ::"+ i)
        }
       /* GlobalScope.launch {
            delay(100L)
            count += 1
            mBinding.tvCount.setText("" + count)
            if (count <= 10000)
                startCouroutine()
        }*/
    }


    fun callApi(){
        var apiRxJava = RetrofiGenerator.getRequestApi();
        GlobalScope.launch(Dispatchers.IO) {
            var response = apiRxJava.getPosts().await()

            if(response.isSuccessful) {
                mBinding.tvText.text = response.body().toString()
                Log.d("aaaaaaaa", response.body().toString())
            }

        }
    }

}
