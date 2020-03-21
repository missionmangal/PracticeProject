package com.myapplication.service_communication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.renderscript.ScriptGroup
import android.widget.Toast
import com.myapplication.R

class ServiceActivity : AppCompatActivity() {

    private var mIsBound: Boolean =false
    var mBoundService:LocalService?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

    }

    override fun onStart() {
        super.onStart()
        doBindService()
    }
    var mConnection  = object:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            mBoundService = null;
            /*Toast.makeText(this@Binding,
                    R.string.local_service_disconnected,
                    Toast.LENGTH_SHORT).show();*/
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
           mBoundService= (service as LocalService.LocalBinder).getService()
        }
    }

    fun doBindService(){
        bindService(Intent(this,LocalService::class.java),mConnection,Context.BIND_AUTO_CREATE)
        mIsBound = true;
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
}
