package com.myapplication.looperHandlerThread2

import java.util.concurrent.ConcurrentLinkedQueue

class SimpleWorker: Thread() {

    private val messageQueue = ConcurrentLinkedQueue<Runnable>()
    var isActive = true

    init {
        start()
    }

    override fun run() {
        super.run()
        while(isActive){
            println("Thread :: ${currentThread()}")
            val runnable = messageQueue.poll()
            runnable?.run()
            try{
                sleep(1000)
            }catch (ex: InterruptedException) {
            }

        }
    }

    fun execute(task: Runnable): SimpleWorker {
        messageQueue.add(task)
        return this
    }

    fun quit() {
        isActive = false
    }
}