package com.myapplication.service_communication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R

class ServiceActivity : AppCompatActivity() , OnClickListener{

    private var mIsBound: Boolean =false
    var startBtn:Button?=null
    var endBtn:Button?=null
    var textBtn:Button?=null
    var mBoundService:LocalService?=null
    lateinit var context :Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        context =this
        startBtn = findViewById(R.id.btn_start)
        endBtn = findViewById(R.id.btn_end)
        textBtn = findViewById(R.id.btn_text)
        startBtn?.setOnClickListener(this)
        endBtn?.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
//        doBindService()
    }
    var mConnection  = object:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            mBoundService = null;
            Toast.makeText(context,"Service is ended ",Toast.LENGTH_LONG).show()
            /*Toast.makeText(this@Binding,
                    R.string.local_service_disconnected,
                    Toast.LENGTH_SHORT).show();*/
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
           mBoundService= (service as LocalService.LocalBinder).getService()
            Toast.makeText(context,"Service is started ",Toast.LENGTH_LONG).show()

            mBoundService?.setCallback( object :LocalService.ServiceCallback{
                override fun sendMessageToActivity(str: String) {
                    textBtn?.setText("$str")
                }
            })
        }
    }

    fun doBindService(){
        if(!mIsBound) {
            var intent = Intent(context, LocalService::class.java)
            intent.putExtra("str","Random")
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
            mIsBound = true;
        }else
            Toast.makeText(this,"Service is already running ",Toast.LENGTH_LONG).show()

    }

    fun doUnBindService(){
        if(mIsBound){
            unbindService(mConnection)
            mIsBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnBindService()
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_start -> doBindService()
            R.id.btn_end -> doUnBindService()
        }
    }
}
