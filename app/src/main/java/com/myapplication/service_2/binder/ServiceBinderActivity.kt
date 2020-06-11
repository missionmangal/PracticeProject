package com.myapplication.service_2.binder

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_service_binder.*

class ServiceBinderActivity: AppCompatActivity() {

    var isBound = false
    var serviceBinder: ServiceBinder?=null
    var mConnection = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
                serviceBinder = null
                isBound = false
            Toast.makeText(this@ServiceBinderActivity,"Service is disconnected",Toast.LENGTH_LONG).show()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            Toast.makeText(this@ServiceBinderActivity,"Service is connected",Toast.LENGTH_LONG).show()
            serviceBinder = (service as ServiceBinder.LocalBinder).getBinder()
            serviceBinder?.callBack = object :ServiceBinder.ServiceCallBack{
                override fun sendResult(random: String) {
                    tv_result.setText(" $random")

                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_binder)
        btn_start.setOnClickListener {
            startBinderService()
        }
        btn_stop.setOnClickListener { stopBinderService() }
    }

    private fun startBinderService() {

        if(!isBound) {
            var intent =Intent(this, ServiceBinder::class.java)
            intent.putExtra("str","Random")
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }else{
            Toast.makeText(this,"Service is already connected",Toast.LENGTH_LONG).show()
        }
    }
    private fun stopBinderService() {

        if(isBound) {
            this.unbindService(mConnection)
            isBound = false
            serviceBinder = null
            Toast.makeText(this@ServiceBinderActivity,"Service is disconnected",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Service is not connected",Toast.LENGTH_LONG).show()
        }
    }
}