package com.myapplication.serviceBinder

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

class ServiceBinder2 : IntentService("name") {

    private val mBinder = LocalBinder()
    var serviceCallback: ServiceCallbacks?=null
    override fun onHandleIntent(intent: Intent?) {
        doWork()
    }

    override fun onCreate() {
        super.onCreate()
    }

    private fun doWork() {
        serviceCallback?.startService()
        for(i in 1..100 ){
            serviceCallback?.progress(i*10)
            Thread.sleep(1000)
        }
        serviceCallback?.serviceEnded()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    fun getRandomNumber() {
        serviceCallback?.receiveResult(Random.nextInt(1500))
    }

    inner class LocalBinder : Binder(){

        fun getServiceInstance(): ServiceBinder2 {
            return  this@ServiceBinder2
        }

    }

    interface ServiceCallbacks {
        fun startService()
        fun progress(i: Int)
        fun serviceEnded()
        fun receiveResult(result: Int)
    }
}