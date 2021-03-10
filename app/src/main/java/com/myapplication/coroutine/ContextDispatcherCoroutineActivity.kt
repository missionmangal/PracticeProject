package com.myapplication.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_context_dispatcher_coroutine.*
import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
class ContextDispatcherCoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_dispatcher_coroutine)
        btn_start.setOnClickListener {
//            testDispatchers()
            testContext()
        }
    }

    private fun testDispatchers() {
        MainScope().launch {
            launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
                println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
                repeat(5) {
                    delay(500)
                    println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
                }
            }
            launch { // context of the parent, main runBlocking coroutine
                println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
                repeat(5) {
                    delay(1000)
                    println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
                }
            }
        }
    }

    private fun testContext(){
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {
                    log("${Thread.currentThread().name} Started in ctx1")
//                    delay(1000)
                    withContext(ctx2) {
                        log("${Thread.currentThread().name} Working in ctx2")
                    }
                    log("${Thread.currentThread().name} Back to ctx1")
                }
            }
        }
    }
}