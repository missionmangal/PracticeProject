package com.myapplication.serviceMessenger2

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_messenger2.*

// Remaining sending message from service to activity

class MessengerActivity2 : AppCompatActivity() {

    private var requestMessenger: Messenger? = null
    private var receiveMessenger: Messenger? = null
    private var isBoundService = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger2)
        setListeners()
    }

    private fun setListeners() {
        this.btnStart?.setOnClickListener {
            connectService()
        }
        btnStop?.setOnClickListener { disconnectService() }
        btnRandomNo?.setOnClickListener {
            sendRequest(MessengerService2.GET_RANDOM_NUMBER, 0)
        }
        btnSquare?.setOnClickListener {
            sendRequest(MessengerService2.GET_SQUARE, 15)
        }
    }

    private fun connectService() {
        if (!isBoundService) {
            val intent = Intent(this, MessengerService2::class.java)
            intent.setPackage("com.myapplication.serviceMessenger2")
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
        } else {
            Toast.makeText(this, "Service is already started", Toast.LENGTH_LONG).show()
        }
    }

    private fun disconnectService() {
        if (isBoundService) {
            isBoundService = false
            unbindService(serviceConnection)
        } else {
            Toast.makeText(this, "Service is not started", Toast.LENGTH_LONG).show()
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            requestMessenger = Messenger(service)
            receiveMessenger = Messenger(ReceiveHandler())
            isBoundService = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            requestMessenger = null
            receiveMessenger = null
            isBoundService = false
        }
    }

    inner class ReceiveHandler() : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MessengerService2.GET_SQUARE -> {
                    tvResult.text = "Square is ${msg.obj}"
                }
                MessengerService2.GET_RANDOM_NUMBER -> {

                    tvResult.text = "Random Number is ${msg.obj}"
                }
            }
        }
    }

    private fun sendRequest(what: Int, obj: Int) {
        if (isBoundService) {
            val msg = Message()
            msg.what = what
            msg.obj = obj
            msg.replyTo = receiveMessenger
            requestMessenger?.send(msg)
        } else {
            Toast.makeText(this, "Service is not connected ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        if (isBoundService) {
            isBoundService = false
            unbindService(serviceConnection)
        }
        super.onDestroy()
    }
}
