package com.myapplication.tutorials.kotlin

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.myapplication.print
import com.myapplication.setImage

fun main(args: Array<String>) {

    var kotlinDay2 = KotlinDay2()
    kotlinDay2.addStrings()
    var str = "Hello world"
    str.print()
}


class KotlinDay2 {

    var str1 : String = "Hello1"
    var str2 : String = "Hello1"

    fun String.addTwoString (str:String):String{

        return this+ str;
    }

    fun addStrings(){
        var str = str1.addTwoString("Hello world")
        System.out.println(str)
    }


    fun KotlinDay1.print(){

    }

}