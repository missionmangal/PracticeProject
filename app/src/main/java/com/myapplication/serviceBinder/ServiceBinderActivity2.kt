package com.myapplication.serviceBinder

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_service_binder2.*

class ServiceBinderActivity2 : AppCompatActivity() {

    private var isStarted = false
    var isBound = false
    var serviceBinder: ServiceBinder2 ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_binder2)
        btnStart.setOnClickListener {
            startService()
        }
        btnBind.setOnClickListener {
            bindServiceMethod()
        }
        btnStop.setOnClickListener {
            disconnectService()
        }

        btnGetRandomNo.setOnClickListener {
            serviceBinder?.getRandomNumber()
        }
    }

    private fun startService() {
        if (!isStarted) {
            val intent = Intent(this@ServiceBinderActivity2, ServiceBinder2::class.java)
            startService(intent)
            isStarted = true
        } else {
            Toast.makeText(this@ServiceBinderActivity2, "Service is already started", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindServiceMethod() {
        if (isStarted) {
            if (!isBound) {
                val intent = Intent(this@ServiceBinderActivity2, ServiceBinder2::class.java)
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
            } else {
                Toast.makeText(this@ServiceBinderActivity2, "Service is already bound", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this@ServiceBinderActivity2, "Service is not started", Toast.LENGTH_SHORT).show()
        }
    }

    private fun disconnectService() {
        if (!isBound) {
            isBound = false
            unbindService(serviceConnection)
        } else {
            Toast.makeText(this@ServiceBinderActivity2, "Service is not started yet", Toast.LENGTH_SHORT).show()
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceBinder = (service as ServiceBinder2.LocalBinder).getServiceInstance()
            serviceBinder?.serviceCallback = serviceCallback
            Toast.makeText(this@ServiceBinderActivity2, "Service started", Toast.LENGTH_SHORT).show()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    val serviceCallback = object : ServiceBinder2.ServiceCallbacks {
        override fun startService() {
        }

        override fun progress(i: Int) {
            if(isBound) {
                tvProgress.text = "Progress $i%"
            }
        }

        override fun serviceEnded() {
            Toast.makeText(this@ServiceBinderActivity2, "Service ended", Toast.LENGTH_SHORT).show()
        }

        override fun receiveResult(result: Int){
            if(isBound) {
                tvResult.text = "Result $result"
                Toast.makeText(this@ServiceBinderActivity2, "Result $result", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ServiceBinderActivity2, "Service is not started yet", Toast.LENGTH_SHORT).show()
            }
        }
    }



}