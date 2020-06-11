package com.myapplication.service_2.binder

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class ServiceBinder : Service() {

    lateinit var mBinder: LocalBinder
    var callBack:ServiceCallBack?=null
    set
    var str:String?=null
    var isRunning = true
    override fun onBind(intent: Intent?): IBinder? {
        str = intent?.getStringExtra("str")
         mBinder=LocalBinder()
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        getRandom()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getRandom()
        return super.onStartCommand(intent, flags, startId)


    }

    private fun getRandom() {
        MainScope().launch {
        for(i in 1..25) {
            if(!isRunning)
                break

                delay(2000)

                var random =Random.nextInt(100,200)
                callBack?.sendResult("$str $random")
                println("$str $random")
            }

        }
    }

    inner class LocalBinder : Binder(){

        fun getBinder():ServiceBinder= this@ServiceBinder

    }

    interface ServiceCallBack{
        fun sendResult(random:String)
    }

    override fun onDestroy() {
        isRunning = false
        super.onDestroy()
    }

}