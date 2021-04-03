package com.myapplication.looperHandlerThread4

import java.util.concurrent.ConcurrentLinkedQueue

class SimpleWorker : Thread() {

    var isActive = true
    var taskQueue = ConcurrentLinkedQueue<Runnable>()
    init {
        start()
    }
    override fun run() {
        super.run()
        while (isActive) {
            val task = taskQueue.poll()
            task?.run()
        }
    }

    fun addTask(runnable: Runnable): SimpleWorker {
        taskQueue.add(runnable)
        return this
    }

    fun quit(){
        isActive = false
    }
}
