package com.myapplication.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_dispatcher.*
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class DispatcherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatcher)
        btnStart.setOnClickListener {
//            ioDispatcher()
//            defaultDispatcher()
//            mainDispatcher()
//            unconfinedDispatcher()
            coroutineScope()
        }
    }

    private fun mainScope(){
        MainScope().launch {
            for(i in 0..5){
                delay(200)
                println("${Thread.currentThread().name} $i Main")
            }
        }
    }

    private fun globalScope(){
        GlobalScope.launch {
            for(i in 0..5){
                delay(200)
                println("${Thread.currentThread().name} $i Global")
            }
        }
    }

    private fun ioDispatcher(){
        CoroutineScope(Dispatchers.IO).launch {
            for(i in 0..5){
                delay(200)
                println("${Thread.currentThread().name}  $i IO")
            }
        }
    }
    private fun defaultDispatcher(){
        CoroutineScope(Dispatchers.Default).launch {
            for(i in 0..5){
                delay(200)
                println("${Thread.currentThread().name}  $i Default")
            }
        }
    }
    private fun mainDispatcher(){
        CoroutineScope(Dispatchers.Main).launch {
            for(i in 0..5){
                delay(200)
                println("${Thread.currentThread().name}  $i Main")
            }
        }
    }
    private fun unconfinedDispatcher(){
        CoroutineScope(Dispatchers.Unconfined).launch {
            for(i in 0..5){
                delay(200)
                println("${Thread.currentThread().name}  $i Unconfined")
            }
        }
    }

    private fun coroutineScope() {
        CoroutineScope(Dispatchers.IO).launch {
            coroutineScope {
                for (i in 0..5) {
                    delay(200)
                    println("${Thread.currentThread().name}  $i coroutineScope")
                }
            }

            for (i in 0..5) {
                delay(200)
                println("${Thread.currentThread().name}  $i outSide coroutine Scope")
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            launch (Dispatchers.Main) {
                for (i in 0..5) {
                    delay(200)
                    println("${Thread.currentThread().name}  $i run blocking")
                    launch (Dispatchers.IO) {
                        println("${Thread.currentThread().name}  $i run blocking")
                    }
                }
            }
            println("${Thread.currentThread().name}  run blocking end")
        }
    }

}