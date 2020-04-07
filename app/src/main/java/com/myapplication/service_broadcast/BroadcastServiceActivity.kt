package com.myapplication.service_broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.myapplication.R
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_messenger_service.*

class BroadcastServiceActivity : AppCompatActivity(){

    lateinit var btnStart:Button
    lateinit var btnStop:Button
    lateinit var btnText:Button
    lateinit var intentService:Intent
    var isRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_service)
        btnStart = findViewById(R.id.btn_start)
        btnStop= findViewById(R.id.btn_stop)
        btnText = findViewById(R.id.btn_text)
        btnStart.setOnClickListener({
            if(!isRunning){
                isRunning = true
                var filter = IntentFilter();
                filter.addAction("com.myapplication")
                LocalBroadcastManager.getInstance(this@BroadcastServiceActivity).registerReceiver(broadcastReceiver,filter)
                intentService = Intent(this@BroadcastServiceActivity,BroadcastService::class.java)
                startService(intentService)
                Toast.makeText(this@BroadcastServiceActivity,"Service started",Toast.LENGTH_LONG).show()

            }else
            Toast.makeText(this,"Service is already running",Toast.LENGTH_LONG).show()

        })
        btnStop.setOnClickListener({
            if(isRunning) {
                LocalBroadcastManager.getInstance(this@BroadcastServiceActivity).unregisterReceiver(broadcastReceiver)
                isRunning = false
                stopService(intentService)
            }else
                Toast.makeText(this@BroadcastServiceActivity,"Service is not running",Toast.LENGTH_LONG).show()
        })
    }

    var broadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
           var count = intent?.getStringExtra("count")
            btnText.setText(count)
            Toast.makeText(this@BroadcastServiceActivity,"$count",Toast.LENGTH_LONG).show()
        }
    }


}
