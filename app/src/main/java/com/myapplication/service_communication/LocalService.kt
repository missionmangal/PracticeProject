package com.myapplication.service_communication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder

class LocalService : Service() {

    private var isRunning= true
    var mBinder:IBinder = LocalBinder()
    var callBack:ServiceCallback ?= null

    fun setCallback( value:ServiceCallback) {this.callBack =value}

    override fun onCreate() {
        super.onCreate()
        countDown(0)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        isRunning = false
        super.onDestroy()
    }

    inner class LocalBinder : Binder(){
        fun getService(): LocalService {
            return this@LocalService
        }
    }


    fun countDown( count:Int){
            if(count<=30&& isRunning)
            Handler().postDelayed({
                var c = count;
                callBack?.sendMessageToActivity(""+(c++))
                countDown(c)
            },1000)
        }


    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    interface ServiceCallback{
        fun sendMessageToActivity(str:String)
    }
}