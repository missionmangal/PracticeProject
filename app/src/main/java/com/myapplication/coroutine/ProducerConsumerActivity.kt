package com.myapplication.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_producer_consumer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProducerConsumerActivity : AppCompatActivity() {
    var channels : Channel<Int> ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producer_consumer)
        btnStart.setOnClickListener {
            channels = Channel()
            produceOffer()
            consumePoll()
        }

    }

    private fun produce(){
        CoroutineScope(Dispatchers.Main).launch {
            for(i in 0 until 10){
                tvProduce.text = "Item Produced $i"
                tvProducerWaiting.visibility = View.VISIBLE
                channels?.send(i)
                tvProducerWaiting.visibility = View.GONE
            }
        }
    }

    private fun consume(){
        CoroutineScope(Dispatchers.Main).launch {
            for(i in 0 until 10){
                delay(4000L)
                val itemNo =channels?.receive()
                tvConsume.text = "Item Received $itemNo"
            }
        }
    }

    private fun produceOffer(){
        CoroutineScope(Dispatchers.Main).launch {
            for(i in 0 until 10){
                tvProduce.text = "Item Produced $i"
                tvProducerWaiting.visibility = View.VISIBLE
                channels?.send(i)
                delay(1000L)
                tvProducerWaiting.visibility = View.GONE
            }
        }
    }

    private fun consumePoll(){
        CoroutineScope(Dispatchers.Main).launch {
            for(i in 0 until 10){
                val itemNo =channels?.poll()
                tvConsume.text = "Item Received $itemNo"
            }
        }
    }
}
