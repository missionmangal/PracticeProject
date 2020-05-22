package com.myapplication.looper_thread

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
            var task = taskQueue.poll()
            if (task != null)
                task.run()
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