package com.myapplication.tutorials.kotlin.avinash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import chap01_extension_functions.InheritanceAndPolymorphism
import com.myapplication.R
import com.myapplication.databinding.ActivityPracticeBinding
import com.myapplication.tutorials.kotlin.avinash.extensions.d
import com.myapplication.tutorials.kotlin.avinash.extensions.toCommaSeparatedString
import com.myapplication.tutorials.kotlin.avinash.extensions.toastL
import com.myapplication.tutorials.kotlin.avinash.extensions.toastS

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

        var derived = InheritanceAndPolymorphism.Derived()
        d(derived.getString())
        toastL(derived.getString())
        /*----------------------------------Extension function test----------------------------------------------------*/



        /*----------------------------------Extension function test----------------------------------------------------*/


        /*----------------------------------Extension function test----------------------------------------------------*/
    }
}