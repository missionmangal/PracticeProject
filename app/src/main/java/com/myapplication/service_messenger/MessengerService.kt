package com.myapplication.service_messenger

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger

class MessengerService : Service() {
    val GET_COUNT =0
    var mBinder = Messenger(RequestHandler())

    @SuppressLint("HandlerLeak")
    inner class RequestHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                0 -> {
                    val message = Message.obtain(null, GET_COUNT)
                    val randomNo = getRandomNumber()
                    message.arg1 = randomNo
                    msg.replyTo.send(message)
                }
            }
            super.handleMessage(msg)

        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder.binder
    }

    fun getRandomNumber ():Int{
        return java.util.Random().nextInt(100)
    }
}