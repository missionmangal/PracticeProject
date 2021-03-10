package com.myapplication.leakedActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.base.MyApplication
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_leaked.*

class LeakedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaked)
        btnNext.setOnClickListener {
            (application as MyApplication).saveActivityInstance(this@LeakedActivity)
            val intent = Intent(this@LeakedActivity, LeakedActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}