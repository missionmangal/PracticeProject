package com.myapplication.service_2.messanger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_messangerctivity2.*
import kotlinx.android.synthetic.main.activity_messenger_service.*
import kotlinx.android.synthetic.main.activity_service_binder.*
import kotlinx.android.synthetic.main.activity_service_binder.btn_start
import kotlinx.android.synthetic.main.activity_service_binder.btn_stop
import kotlinx.android.synthetic.main.activity_service_binder.tv_result

class Messangerctivity2 : AppCompatActivity() {

    private var count: Int =1
    lateinit var mConnection : ServiceConnection
    var receiveMessenger : Messenger?= null
    var requestMessenger: Messenger ?= null
    var isBound = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messangerctivity2)
        createConnection()
        btn_start.setOnClickListener { connectService() }
        btn_stop.setOnClickListener { disconnectService() }
        btn_request21.setOnClickListener { requestMessage() }
    }

    private fun disconnectService() {
        if(isBound){
            isBound =false
            unbindService(mConnection)
            Toast.makeText(this@Messangerctivity2,"Service disconnected" , Toast.LENGTH_LONG).show()
            receiveMessenger = null
            requestMessenger = null
        }else{

            Toast.makeText(this@Messangerctivity2," Servie is not connected", Toast.LENGTH_LONG).show()
        }
    }

    private fun connectService() {
        if(!isBound) {
            var intent = Intent(this, MessengerService2::class.java)
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }else{
            Toast.makeText(this@Messangerctivity2," Servie is already connected", Toast.LENGTH_LONG).show()
        }
    }

    private fun createConnection() {
        mConnection = object :ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
                                
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                receiveMessenger = Messenger(ReceiveHandler())
                requestMessenger = Messenger(service)
                Toast.makeText(this@Messangerctivity2," Servie connected", Toast.LENGTH_LONG).show()
                isBound = true;
            }
        }
    }

    
    fun requestMessage(){
        if(isBound) {
            var msg = Message.obtain(null, 100)
            count++
            var request = "Request No. $count"
            msg.obj = request
            msg.replyTo = receiveMessenger
            requestMessenger?.send(msg)
            tv_request12.setText(request)
        }else
            Toast.makeText(this@Messangerctivity2," Servie is not connected", Toast.LENGTH_LONG).show()
    }

    
    
    
    inner class ReceiveHandler : Handler() {
        override fun handleMessage(msg: Message) {
            var message = msg.obj
            when(msg.what){
                100-> tv_result12.setText("Result is $message")
            }
            
        }
    }
}

