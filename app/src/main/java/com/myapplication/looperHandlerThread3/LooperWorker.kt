package com.myapplication.looperHandlerThread3

import java.util.concurrent.ConcurrentLinkedQueue

class LooperWorker : Thread() {

    var isActive = true
    var messageQueue = ConcurrentLinkedQueue<Runnable>()

    init {
        start()
    }
    override fun run() {
        super.run()
        while (isActive){
            val runnable = messageQueue.poll()
            runnable?.run()
        }
    }

    fun execute(runnable: Runnable): LooperWorker {
        messageQueue.add(runnable)
        return this
    }

    fun quit() {
        isActive = false
    }
}