package com.myapplication.looperHandlerThread

import android.os.Handler
import android.os.HandlerThread
import android.widget.TextView


class LooperSimpleWorker( name:String,tv:TextView)  :HandlerThread(name) {

    var handler:Handler
    init {
       start()
       handler = Handler(looper)
        tv.text = "In Looper Text INIT"
//        Thread.sleep(2000)
    }

    override fun run() {
        super.run()
        println("Looper Thread name : " + Thread.currentThread().name)
    }

    fun execute(runnable: Runnable):LooperSimpleWorker{
        handler.post(runnable)

        println("Looper Thread name : " + Thread.currentThread().name)
        return this
    }


}