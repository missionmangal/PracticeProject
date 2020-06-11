package com.myapplication.coroutine

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_coroutine1.*
import kotlinx.coroutines.*

class CoroutineActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine1)
        btn_start_count.setOnClickListener {
            count()
        }
        btn_Next.setOnClickListener {
            var intent = Intent(this@CoroutineActivity1, CoroutineActivity2::class.java)
            startActivity(intent)
        }
    }
    var job2:Job?=null
    var job:Job?=null
    var mainScope =MainScope()
    fun count(){
//        MainScope(Dispatchers.IO).launch {  }

         job=  MainScope().launch() {
            for(i in 11..40){

                    println("$this@CoroutineActivity1 countMain =$i  ${Thread.currentThread().name}")

                    tv_count.setText("$this@CoroutineActivity1 count =$i  ${Thread.currentThread().name}")
                delay(1000L)
                if(i==25)
                    job?.cancel("My Choice to cancel")
            }
        }
        lifecycleScope.launch(){
            delay(5000)
            var intent = Intent(this@CoroutineActivity1, CoroutineActivity2::class.java)
            startActivity(intent)
            finish()
        }
        job?.invokeOnCompletion {
            if(it is Throwable){
                println("Coroutine canceled ${it.message}")
            }else
                println("Coroutine completed")
        }
    }

    override fun onStop() {
        super.onStop()
//        CoroutineScope(Dispatchers.IO).cancel()
//        mainScope.cancel()
//        job?.cancel()
    }
}
