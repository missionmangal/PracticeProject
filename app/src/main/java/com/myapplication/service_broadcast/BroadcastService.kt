package com.myapplication.service_broadcast

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class BroadcastService : IntentService("BroadcastService") {


    override fun onHandleIntent(intent: Intent?) {
                sendCount(0)
    }


    private fun sendCount(i: Int) {
//        Handler().postDelayed({
            val broadcastIntent = Intent("com.myapplication")
            broadcastIntent.putExtra("count","its no $i")
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
            if(i==10){
                stopSelf()
                return
            }else
                sendCount(i+1)
//        },3000)
    }


}