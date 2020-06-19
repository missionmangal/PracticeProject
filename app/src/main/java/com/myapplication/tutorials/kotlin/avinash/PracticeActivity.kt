package com.myapplication.tutorials.kotlin.avinash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.myapplication.R
import com.myapplication.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {

    var binding : ActivityPracticeBinding? = null
    var arrayList = arrayListOf<String>("A","B","C","D","E","F","G","H","I")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_practice)

        /*----------------------------------Extension function test----------------------------------------------------*/
        d(arrayList.toCommaSeparatedString())
        toastS(arrayList.toCommaSeparatedString())
        toastL(arrayList.toCommaSeparatedString())
        /*----------------------------------Extension function test----------------------------------------------------*/
    }
}