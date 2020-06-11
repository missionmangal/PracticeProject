package com.myapplication.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.act_shared_value.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis


class CoroutineSharedValue : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_shared_value)
        var counterContext = newSingleThreadContext("CounterContext")
        var mutex = Mutex()
        btn_start.setOnClickListener {
//                counter =
                runBlocking {
//                CoroutineScope(Dispatchers.Default).launch {
                withContext(Dispatchers.Default){
                    startcounter {
                        mutex.withLock  {
                        counter++
                    }
                    }
//                }
                }
            }

        }
        println("counter is $counter")
        tv_counter.setText("Counter is $counter")
    }

//    var counter:AtomicInteger = AtomicInteger(1)
    var counter = 1

    suspend fun startcounter( action:suspend ()->Unit) {

        val time = measureTimeMillis {
            coroutineScope {
                repeat(100) {
                    launch {
                        repeat(100) {
                            action()
                        }
                    }
                }
            }
        }
        println("Time taken is $time")
//        println("counter is ${counter.get()}  ${Thread.currentThread().name}")
//        tv_counter.setText("Counter is ${counter.get()} ms")
        println("counter is ${counter}  ${Thread.currentThread().name}")
        tv_counter.setText("Counter is ${counter} ms")
    }

}