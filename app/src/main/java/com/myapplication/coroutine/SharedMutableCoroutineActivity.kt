package com.myapplication.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.shared_mutable_coroutine_activity.*
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class SharedMutableCoroutineActivity : AppCompatActivity() {

    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_mutable_coroutine_activity)
        btnStart.setOnClickListener {
//            startCountingWithoutSyncronization()
//            startCountingWithAtomic()
//            startCountingWihtConfinedFine()
            startCountingWihtConfinedGross()
        }
    }


//    Start 1000000 coroutine without syncronization

    @ExperimentalTime
    fun startCountingWithoutSyncronization() {
        val counter: Counter = Counter()
        runBlocking {
            withContext(Dispatchers.Default) {
                massiveRun {
                    counter.count++;
                    counter.text = "Text ${counter.count}"
                }
            }
        }
        println("count is ${counter.count} \ntext is ${counter.text}")
        tvResult.text = ("count is ${counter.count} \ntext is ${counter.text} ")
    }

    suspend fun massiveRun(action: suspend () -> Unit) {
        val n = 100  // number of coroutines to launch
        val k = 1000 // times an action is repeated by each coroutine
        val time = measureTimeMillis {
            coroutineScope { // scope for coroutines
                repeat(n) {
                    launch {
                        repeat(k) { action() }
                    }
                }
            }
        }
        println("Completed ${n * k} actions in $time ms")
    }


    class Counter {
        var count = 0;
        var text = "";
    }

//    End


    //    Start 1000000 coroutine with atomic.
//    Thread-safe data structures
    @ExperimentalTime
    fun startCountingWithAtomic() {
        var count = AtomicInteger(0)
        val counter = CounterAtomic()
        val time = measureTime {
            runBlocking {
                massiveRun {
                    count.incrementAndGet()
                    counter.count.incrementAndGet()
                    counter.text = "Text ${counter.count}"
                }
            }
        }
        println("count is ${counter.count} \ntext is ${counter.text} \n time = $time new count = $count")
        tvResult.text = ("count is ${counter.count} \ntext is ${counter.text} \n time = $time new count = $count")
    }

    class CounterAtomic {
        var count: AtomicInteger = AtomicInteger(0)
        var text: String = ""
    }

//    End

//    Thread confinement fine-grained

    fun startCountingWihtConfinedFine() {
        val coroutineThread = newSingleThreadContext("confinedFine")
        val counter = Counter()
        val time = measureTimeMillis {
            runBlocking {
                massiveRun {
                    withContext(coroutineThread) {
                        counter.count++
                        counter.text = "Text ${counter.count}"
                    }
                }
            }
        }
        println("count is ${counter.count} \ntext is ${counter.text} \n time = $time")
        tvResult.text = ("count is ${counter.count} \ntext is ${counter.text} \n time = $time")
    }
//    End
//    Thread confinement fine-grained

    fun startCountingWihtConfinedGross() {
        val coroutineThread = newSingleThreadContext("confinedFine")
        val counter = Counter()
        val time = measureTimeMillis {
            runBlocking {
                withContext(coroutineThread) {
                    massiveRun {
                        counter.count++
                        counter.text = "Text ${counter.count}"
                    }
                }
            }
        }
        println("count is ${counter.count} \ntext is ${counter.text} \n time = $time")
        tvResult.text = ("count is ${counter.count} \ntext is ${counter.text} \n time = $time")
    }
//    End
}