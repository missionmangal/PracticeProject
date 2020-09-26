package com.myapplication.calculator

import android.util.Log

class CalculatorOperations {

    fun add(x:Int,y:Int):Int = x+y

    fun subtraction(x: Int,y: Int) = x-y

    fun multiply(x: Int,y: Int) = x*y

    fun divide(x: Int,y: Int):Int {
        if(y>0)
            return x/y;
        else
            Log.d("Error","Please insert correct value")
        return 0;
    }
}