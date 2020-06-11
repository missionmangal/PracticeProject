package com.myapplication.service_2.messanger

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import kotlin.random.Random

class MessengerService2 :Service(){

    private val messagnerHandler  = RequestHandler()
    var mBinder: Messenger = Messenger(messagnerHandler)


    inner class RequestHandler : Handler(){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                100->{
                    var request = msg.obj
                    var message = Message.obtain(null,100)
                    message.obj = " ${getRandomNo()}"
                    msg.replyTo.send(message)
                }
            }
        }
    }

    private fun getRandomNo(): Int {
        return Random.nextInt(100,200)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder.binder
    }

}