package com.myapplication.launchMode.singleInstance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.myapplication.MainActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_single_instance.*
import kotlinx.coroutines.MainScope

class SingleInstanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_instance)
        btnStartAgain.setOnClickListener {
            val intent = Intent(this@SingleInstanceActivity, SingleInstanceActivity::class.java)
            startActivity(intent)
        }
        btnStartMain.setOnClickListener {
            val intent = Intent(this@SingleInstanceActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "OnNewIntent", Toast.LENGTH_LONG).show()
    }
}