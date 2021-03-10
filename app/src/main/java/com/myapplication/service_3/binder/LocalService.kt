package com.myapplication.service_3.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocalService : Service() {
    private var isRunning : Boolean = false
    private val mBinder :IBinder = LocalBinder()
    var callback: ServiceCallback ?=null

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        getRandomNumber()
    }

    override fun onDestroy() {
        isRunning = false
        super.onDestroy()
    }

    inner class LocalBinder : Binder() {
        fun getService() = this@LocalService

    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    interface ServiceCallback {
        fun sendProgress(progress : Int)
    }

    private fun getRandomNumber(){
        for(i in 1..25){
            if(isRunning) {
                MainScope().launch {
                    delay(1000)
                    callback?.sendProgress(i)
                }
            }else {
                break
            }
        }
    }
}