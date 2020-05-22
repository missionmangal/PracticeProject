package com.myapplication.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.myapplication.R
import com.myapplication.databinding.ActivityChannelBinding
import com.myapplication.databinding.ActivityCoroutineBindingImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.*
import kotlin.collections.ArrayList

class ChannelActivity : AppCompatActivity() {

    var mBinding: ActivityChannelBinding? = null
    var productList: LinkedList<String> = LinkedList()
    var count = 1;
    val bufferSize = 5
    val channel = Channel<Int>(Channel.UNLIMITED)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_channel)
        mBinding?.let {
            it.btnStart.setOnClickListener {
//                testChannel()
//                produce()
//                consume()
//                testBufferChannel()
                produceBuffer()
                consumeBuffer()
            }
        }
    }

    private fun testBufferChannel() {

        val channel = Channel<Int>(2)

// Coroutine#1
        MainScope().launch {
            var i=0;
            while(i<10) {
                i++
                println("demo::"+ "send $i")
                channel.send(i)
            }
            println("demo"+ "send done")
        }

// Coroutine#2
        MainScope().launch {
            var i=0;
            while(i<10) {
                i++
                println("demo::"+ "receive ${channel.receive()}")
            }
            println("demo::" + "receive done")
        }
    }

    fun produce() {
        MainScope().launch {
            while (true) {
                if (productList.size >= bufferSize) {
                    println("/////////////Producer waiting//////////////")
                    channel.send(count)
                }
                var product = "Product :: $count"
                count =count+1
                productList.add(product)
                println("******Produced $product")
                runBlocking {
                    delay(300)
                }
                if(count>=25) {
                    channel.send(count)
                    break
                }
            }
        }
    }

    fun consume(){
        MainScope().launch {
            while(true) {
                if(productList.size>0){
                    var product = productList.last
                    productList.removeLast()
                    println("-----Consumed $product")
                    runBlocking {
                        delay(600)
                    }
                }else
                    channel.receive()
            }
        }
    }
    fun produceBuffer() {
        MainScope().launch {
            while (true) {
//                if (productList.size >= bufferSize) {
//                    println("/////////////Producer waiting//////////////")
//                    channel.send(count)
//                }
                channel.send(count)
                var product = "Product :: $count"
                count =count+1
                productList.add(product)
                println("******Produced $product")
                runBlocking {
                    delay(300)
                }
                if(count>=25) {
                    channel.send(count)
                    break
                }
            }
        }
    }

    fun consumeBuffer(){
        MainScope().launch {
            while(true) {
                if(productList.size>0) {

                    var product = productList.last
                    productList.removeLast()
                    println("-----Consumed $product")
                    runBlocking {
                        delay(300)
                    }
                }
//                }else
                    channel.receive()
            }
        }
    }


    private fun testChannel() {
        sendValues()
        receiveValues()


    }

    @Synchronized
    fun sendValues() {
        GlobalScope.launch {
            (1..5).forEach {
                println("Channel Send :: $it")
                channel.send(it)
            }
        }
    }

    @Synchronized
    fun receiveValues() {
        GlobalScope.launch {
            repeat(5) {
                println("Before Channel Receiving :: ")
                println("Channel Receive :: ${channel.receive()}")
                println("After Channel Receiving :: ")
                delay(5000)
            }
        }
    }
}
