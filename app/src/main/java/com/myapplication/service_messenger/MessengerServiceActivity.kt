package com.myapplication.service_messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.myapplication.R

class MessengerServiceActivity : AppCompatActivity() {

    var btnStart: Button?=null
    var btnStop: Button?=null
    var btnRandomNo: Button?=null
    var requestMessenger : Messenger?= null
    var receiveMessenger : Messenger?= null
    var isBoundservice = false;
    var GET_RANDOM_NO =0;
    var serviceConnection = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            Toast.makeText(this@MessengerServiceActivity,"Service disconnected" , Toast.LENGTH_LONG).show()
            receiveMessenger = null
            requestMessenger = null
            isBoundservice = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Toast.makeText(this@MessengerServiceActivity,"Service connected" , Toast.LENGTH_LONG).show()
            requestMessenger = Messenger(service)
            receiveMessenger = Messenger(ReceiveHandler())
            isBoundservice = true
        }
    }

//    Handler
   inner class ReceiveHandler : Handler(){

    override fun handleMessage(msg: Message) {
        when(msg.what){
            0-> {
                var randomNo = msg.arg1
                Toast.makeText(this@MessengerServiceActivity," random no is $randomNo",Toast.LENGTH_LONG).show()
            }
        }
        super.handleMessage(msg)
    }
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger_service)
        btnStart = findViewById(R.id.btn_start)
        btnStop = findViewById(R.id.btn_stop)
        btnRandomNo = findViewById(R.id.btn_random_no)
        btnStart?.setOnClickListener({
            connectService()
        })
        btnStop?.setOnClickListener({disconnectService()})
        btnRandomNo?.setOnClickListener({fetchRandomNo()})
    }

    private fun disconnectService() {
        if(isBoundservice)
            unbindService(serviceConnection)
        else
            Toast.makeText(this@MessengerServiceActivity,"Servie is not connected",Toast.LENGTH_LONG).show()

    }

    fun connectService(){

        if(!isBoundservice) {
            var intent = Intent(this, MessengerService::class.java)
            intent.setPackage("com.myapplication.service_messenger")
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }else{
            Toast.makeText(this@MessengerServiceActivity,"Servie is not connected",Toast.LENGTH_LONG).show()

        }
    }

    fun fetchRandomNo(){
        if(isBoundservice){
            var message = Message.obtain(null,GET_RANDOM_NO)
            message.replyTo = receiveMessenger
            try{
                requestMessenger?.send(message)
            }catch (e:RemoteException){
                e.fillInStackTrace()
            }
        }else
            Toast.makeText(this@MessengerServiceActivity," Servie is not connected",Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        if(isBoundservice)
        unbindService(serviceConnection)
        super.onDestroy()
    }
}
