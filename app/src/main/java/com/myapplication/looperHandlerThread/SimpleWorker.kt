package com.myapplication.looperHandlerThread

import java.util.concurrent.ConcurrentLinkedQueue

class SimpleWorker()  :Thread() {
   var taskQueue = ConcurrentLinkedQueue<Runnable>()
   var isActive = true
   init {
       start()
   }

    override fun run() {
        println("Thread :: ${currentThread()}")
        while(isActive) {
            taskQueue.poll()?.run()
        }
        println("Thread Over:: ${currentThread()}")
    }

    fun execute(runnable: Runnable):SimpleWorker{
        taskQueue.add(runnable)
        return this
    }

    fun quit(){
        isActive =false
    }

}