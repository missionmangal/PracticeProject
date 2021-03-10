package com.myapplication.service_3.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_binder_service.*

class BinderServiceActivity : AppCompatActivity() {
    private var mIsBound: Boolean = false
    private var mService: LocalService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_service)
        setListener()
    }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = (service as LocalService.LocalBinder).getService()
            mService?.callback = object : LocalService.ServiceCallback {
                override fun sendProgress(progress: Int) {
                    if(mIsBound)
                        Toast.makeText(this@BinderServiceActivity, progress.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mService = null
        }
    }

    var intentService :Intent ?= null
    private fun doBindService() {
        if (!mIsBound) {
            intentService = Intent(this, LocalService::class.java)
            intentService?.putExtra("str", "Random")
            bindService(intentService, mServiceConnection, Context.BIND_AUTO_CREATE)
            mIsBound = true
        } else
            Toast.makeText(this, "Service is already running ", Toast.LENGTH_LONG).show()

    }

    private fun doUnBindService() {
        if (mIsBound) {
            unbindService(mServiceConnection)
            stopService(intentService)
            mIsBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnBindService()
    }

    private fun setListener() {
        btn_start.setOnClickListener { doBindService() }
        btn_end.setOnClickListener { doUnBindService() }
    }
}