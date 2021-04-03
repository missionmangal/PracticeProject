package com.myapplication.serviceMessenger2

import android.app.Service
import android.content.Intent
import android.os.*
import kotlin.random.Random

class MessengerService2 : Service() {

    private val messenger = Messenger(RequestHandler())
    private val serviceCallBack: ServiceCallBack ?= null

    companion object {
        const val GET_RANDOM_NUMBER = 1
        const val GET_SQUARE = 2
    }

    override fun onBind(intent: Intent?): IBinder? {
        serviceCallBack?.serviceStarted()
        return messenger.binder
    }

    interface ServiceCallBack {
        fun serviceStarted()
        fun serviceStopped()
    }

    class RequestHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var result = 0
            when (msg.what) {
                GET_RANDOM_NUMBER -> {
                    result = fetchRandomNumber()
                }
                GET_SQUARE -> {
                    result = getSquare(msg.obj as Int)
                }
            }
            val message = Message()
            message.what = msg.what
            message.obj = result
            msg.replyTo.send(message)
        }

        private fun getSquare(value: Int): Int {
            return value * value
        }

        private fun fetchRandomNumber(): Int {
            return Random.nextInt(100)
        }
    }

}
