package com.myapplication.looper_thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_looper.*
import kotlin.concurrent.thread

class LooperActivity : AppCompatActivity() {

    var count =0;
    var handler =object :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            tv_count.setText(msg.obj.toString())
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
        var simpleWorker = SimpleWorker()
        simpleWorker.execute(Runnable {
            Thread.sleep(2000)
            count+=1
            var msg = Message()
            msg.obj = count
            println("First ${count}")
            println("First ${Thread.currentThread().name}")
            handler.sendMessage(msg)
        }).execute(Runnable {
            Thread.sleep(2000)
            count+=1
            var msg = Message()
            msg.obj = count
            println("Second ${count}")
            println("Second ${Thread.currentThread()}")
            handler.sendMessage(msg)
        }).execute(Runnable {
            Thread.sleep(2000)
            count+=1
            var msg = Message()
            msg.obj = count
            handler.sendMessage(msg)
            println("Third ${count}")
            println("Third ${Thread.currentThread()}")
        })

        simpleWorker.execute(Runnable {
            Thread.sleep(2000)
            count+=1
            var msg = Message()
            msg.obj = count
            handler.sendMessage(msg)
            println("Forth ${count}")
            println("Forth ${Thread.currentThread()}")
        }).execute(Runnable {
            Thread.sleep(2000)
            count+=1
            var msg = Message()
            msg.obj = count
            handler.sendMessage(msg)
            println("Fivth ${count}")
            println("Fivth ${Thread.currentThread()}")
            simpleWorker.quit()
        })
    }
}
