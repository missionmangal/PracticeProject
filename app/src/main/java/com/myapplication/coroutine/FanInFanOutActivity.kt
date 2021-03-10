package com.myapplication.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_fan_in_fan_out.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FanInFanOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan_in_fan_out)
        btnFanIn.setOnClickListener {
            demoFanIn()
        }
        btnFanOut.setOnClickListener {
            demoFanOut()
        }
    }

    //    Fan In Start
    private suspend fun sendString(channel: SendChannel<String>, value: String, time: Long) {
        while (true) {
            delay(time)
            channel.send(value)
        }
    }

    fun demoFanIn() {
        val channel = Channel<String>()
        MainScope().launch {
            launch {
                sendString(channel, "Vishnu", 200L)
            }
            launch {
                sendString(channel, "Soni", 300L)
            }
            launch {
                repeat(20) {
                    tvFanInText.setText(channel.receive())
                    System.out.println(channel.receive())
                }
                channel.cancel()
            }
        }
    }


//    Fan In End

    //    Fan Out Start
    fun demoFanOut() {
        val channel = Channel<Int>()
        System.out.println("Fan Out Process")
        MainScope().launch {
            launch {
                sendInt(channel, 200L)
            }
            repeat(10){
            launch {
                    receiveString(it , channel)
                }

            }
            delay(3000L)
            channel.cancel()
        }
    }

    private suspend fun sendInt(channel: Channel<Int>, time: Long) {
        var x =1;
        while (true){
            channel.send(x++)
            delay(time)
        }
    }

    private suspend fun receiveString(id: Int, channel: Channel<Int>) {
        for (msg in channel){
            tvFanOutText.setText("Processor= #${id} message = $msg")
            System.out.println("Processor= #${id} message = $msg")
        }
    }
//    Fan Out End
}