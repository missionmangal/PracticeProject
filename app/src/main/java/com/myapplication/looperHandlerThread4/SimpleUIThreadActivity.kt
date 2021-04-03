package com.myapplication.looperHandlerThread4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_simple_ui_thread.*

class SimpleUIThreadActivity : AppCompatActivity() {

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            tvStatus.text = msg.obj.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_ui_thread)
        btnStartThread.setOnClickListener {
//            startThread()
            startLooperThread()
        }
    }

    private fun startLooperThread() {
        val worker = WorkerWithLooper("Worker thread")
        worker.addTask(Runnable {
            task(1)
        }).addTask(Runnable {
            task(2)
        }).addTask(Runnable {
            task(3)
        }).addTask(Runnable {
            task(4)
        }).addTask(Runnable {
            task(5)
        })
    }

    private fun task(taskNo: Int) {
        var msg = Message.obtain()
        msg.obj = "Looper Task $taskNo started"
        handler.sendMessage(msg)
        Thread.sleep(3000)
        msg = Message.obtain()
        msg.obj = "Looper Task $taskNo ended"
        handler.sendMessage(msg)
        Thread.sleep(2000)
    }

    private fun startThread() {
        val worker = SimpleWorker()
        worker.addTask(Runnable {
            task(1)
        }).addTask(Runnable {
            task(2)
        }).addTask(Runnable {
            task(3)
        }).addTask(Runnable {
            task(4)
        }).addTask(Runnable {
            task(5)
        })
    }
}
