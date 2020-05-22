package com.myapplication.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_channel.*
import kotlinx.android.synthetic.main.activity_coroutine1.*
import kotlinx.android.synthetic.main.activity_coroutine1.tv_count
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class CoroutineActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine1)
        btn_start_count.setText("Start Count Next")
        btn_start_count.setOnClickListener {
            count()
        }
    }
    var job2:Job?=null
    var job:Job?=null
    fun count(){
//        MainScope(Dispatchers.IO).launch {  }


        job =CoroutineScope(Dispatchers.IO).launch() {
            for(i in 11..20){
                println("count =$i  ${Thread.currentThread().name}")
//                launch {
                    println("countMain =$i  ${Thread.currentThread().name}")
                    tv_count.setText("count =$i  ${Thread.currentThread().name}")
//                }

                delay(2000L)
            }
        }
    }
}
