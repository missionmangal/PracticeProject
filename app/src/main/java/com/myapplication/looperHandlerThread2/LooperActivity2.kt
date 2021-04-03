package com.myapplication.looperHandlerThread2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_looper_thread2.*

class LooperActivity2 : AppCompatActivity() {

    private var simpleWorker: SimpleWorker? = null
    var count = 1
    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            println("Get Handled Message " + msg.obj.toString() + Thread.currentThread())
            tv_count.text = msg.obj.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper_thread2)
        btn.setOnClickListener {
            simpleWorker = SimpleWorker()
            simpleWorker?.execute(Runnable {
                val message = Message()
                message.obj = count++
                handler.sendMessage(message)
                println("First" + Thread.currentThread())
            })
            simpleWorker?.execute(Runnable {
                val message = Message()
                message.obj = count++
                handler.sendMessage(message)
                println("Second" + Thread.currentThread())
            })
            simpleWorker?.execute(Runnable {
                val message = Message()
                message.obj = count++
                handler.sendMessage(message)
                println("Third" + Thread.currentThread())
            })
            simpleWorker?.execute(Runnable {
                val message = Message()
                message.obj = count++
                handler.sendMessage(message)
                println("Forth" + Thread.currentThread())
            })
            simpleWorker?.execute(Runnable {
                val message = Message()
                message.obj = count++
                handler.sendMessage(message)
                println("Fifth" + Thread.currentThread())
                simpleWorker?.quit()
            })
        }
    }
}