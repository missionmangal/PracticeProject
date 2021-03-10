package com.myapplication.leakedActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.base.MyApplication
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_leaked2.*

class LeakedActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaked2)
        btnNext.setOnClickListener {
//            System.gc()
            (application as MyApplication).leakedActivity?.get()?.let {
                System.out.println(it.toString())
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            }?: Toast.makeText(this, "reference has been garbage collected" , Toast.LENGTH_LONG).show()
        }
    }
}