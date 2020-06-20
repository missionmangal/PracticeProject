package com.myapplication.tutorials.kotlin.avinash.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import chap01_extension_functions.ExtensionFunction

/*----------ArrayList of String -> TO -> Comma separated string---------*/
fun ArrayList<String>.toCommaSeparatedString() : String{
    var builder = StringBuilder("")

    for (str in this)
        if(!str.isEmpty())
            builder.append("$str,")

    return builder.toString().removeSuffix(",")
}
/*----------ArrayList of String -> TO -> Comma separated string---------*/



/*----------Toast---------*/
fun Context.toastS(str:String){
    Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
}
fun Context.toastL(str:String){
    Toast.makeText(this,str,Toast.LENGTH_LONG).show()
}
/*----------Toast---------*/



/*----------LOG---------*/
fun Context.d(str:String){
    Log.d("log",str)
}
/*----------LOG---------*/



/*----------Get second character of string---------*/
fun String.second(): Char {
    if (isEmpty()) throw NoSuchElementException("Char sequence is empty.")
    if (length < 2) throw NoSuchElementException("Char sequence length is less than 2.")
    return this[1]
}
/*----------Get second character of string---------*/



/*----------Start activity / Pass bundle---------*/
fun Context.startAct(className: Class<*>, key:String?=null, bundle : Bundle?= null){
    this.startActivity(Intent(this,className).putExtra(key,bundle))
}
/*----------Start activity / Pass bundle---------*/

fun Double.plus(someValue:Double) : Double{
    return this+someValue
}
fun Double.minus(someValue:Double) : Double{
    return this-someValue
}
fun Double.mul(someValue:Double) : Double{
    return this*someValue
}
fun Double.percent(howMuchPercent:Double) : Double{
    return (this/100.0)*howMuchPercent
}



/*----------Extension properties---------*/
var String.developerName :String
    get (){
        return "Avinash"
    }
    set(value) {
        developerName = value
    }

var ExtensionFunction.respestfulName: String
    get() = this.name
    set(value) {
        this.name = value+" Ji"
    }
/*----------Extension properties---------*/
