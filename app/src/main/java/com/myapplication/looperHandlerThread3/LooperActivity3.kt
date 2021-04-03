package com.myapplication.looperHandlerThread3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_looper3.*

class LooperActivity3 : AppCompatActivity() {

    var count = 1

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val message = msg.obj
            tvCount.text = message.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper3)
        btnStart.setOnClickListener {
            val looperWorker = LooperWorker()
            for (i in 1..5) {
                looperWorker.execute(Runnable {
                    val msg = Message()
                    msg.obj = count++
                    handler.sendMessage(msg)
                    if (count % 4 == 0)
                        looperWorker.quit()
                    Thread.sleep(5000)
                })
                println("count = $i")
            }
        }
    }
}