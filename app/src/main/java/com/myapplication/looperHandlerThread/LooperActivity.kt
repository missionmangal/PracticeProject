package com.myapplication.looperHandlerThread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_looper.*

class LooperActivity : AppCompatActivity() {

    var count = 0;
    var handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            tv_count.text = msg.obj.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper)
//        startWorkerThread()
        println("Main Thread :: ${Thread.currentThread()}")
        btn.setOnClickListener {
            println("Main Thread 2 :: ${Thread.currentThread()}")
            startWorkerThread()
        }
    }

    private fun startWorkerThread() {
//        var simpleWorker = SimpleWorker("Looper Thread",tv_count)
        val simpleWorker = SimpleWorker()
        simpleWorker.execute(Runnable {
            Thread.sleep(2000)
            count += 1
            val msg = Message()
            msg.obj = count
            println("First ${count}")
            println("First ${Thread.currentThread().name}")
            handler.sendMessage(msg)
        }).execute(Runnable {
            Thread.sleep(2000)
            count += 1
            val msg = Message()
            msg.obj = count
            println("Second ${count}")
            println("Second ${Thread.currentThread()}")
            handler.sendMessage(msg)
        }).execute(Runnable {
            Thread.sleep(2000)
            count += 1
            val msg = Message()
            msg.obj = count
            handler.sendMessage(msg)
            println("Third ${count}")
            println("Third ${Thread.currentThread()}")
        })

        simpleWorker.execute(Runnable {
            Thread.sleep(2000)
            count += 1
            val msg = Message()
            msg.obj = count
            handler.sendMessage(msg)
            println("Forth ${count}")
            println("Forth ${Thread.currentThread()}")
        }).execute(Runnable {
            Thread.sleep(2000)
            count += 1
            val msg = Message()
            msg.obj = count
            handler.sendMessage(msg)
            println("Fivth ${count}")
            println("Fivth ${Thread.currentThread()}")
            simpleWorker.quit()
        })
    }
}
