package com.myapplication.looperHandlerThread4

import android.os.Handler
import android.os.HandlerThread

class WorkerWithLooper(private val threadName: String) : HandlerThread(threadName) {

    var handler : Handler?= null

    init {
        start()
        handler = Handler(looper)
    }

    override fun run() {
        super.run()
        println("Looper Thread name : " + Thread.currentThread().name)
    }

    fun addTask(task: Runnable): WorkerWithLooper {
        handler?.post(task)
        println("Looper Thread name : " + Thread.currentThread().name)
        return this
    }
}