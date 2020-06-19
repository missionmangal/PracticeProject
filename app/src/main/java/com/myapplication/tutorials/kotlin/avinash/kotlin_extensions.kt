package com.myapplication.tutorials.kotlin.avinash

import android.content.Context
import android.util.Log
import android.widget.Toast

fun ArrayList<String>.toCommaSeparatedString() : String{
    var builder = StringBuilder("")

    for (str in this)
        if(!str.isEmpty())
            builder.append("$str,")

    return builder.toString().removeSuffix(",")
}

fun Context.toastS(str:String){
    Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
}
fun Context.toastL(str:String){
    Toast.makeText(this,str,Toast.LENGTH_LONG).show()
}
fun Context.d(str:String){
    Log.d("log",str)
}
