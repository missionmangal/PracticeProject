package com.myapplication.service_communication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder

class LocalService : Service() {

    var mBinder:IBinder ?=null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        countDown()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class LocalBinder : Binder(){
        fun getService(): LocalService {
            return this@LocalService
        }
    }


    fun countDown(){
        for(i in 1..30){
            Handler().postDelayed({

            },1000)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    interface serviceAction{
        fun sendMessageToActivity(str:String)
    }
}