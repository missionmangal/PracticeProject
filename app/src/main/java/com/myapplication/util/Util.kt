package com.myapplication.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun Glide.setImage(url:String, context: Context, view: ImageView){
    Glide.with(context).load(url).into(view)
}


fun String.print(){
    System.out.println(this)
}