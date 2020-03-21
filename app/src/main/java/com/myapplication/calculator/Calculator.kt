package com.myapplication.calculator

class Calculator(private val operators:Operators) {

    fun addTwoNumbers(a:Int,b:Int):Int = 2*operators.add(a,b)
    fun subtractTwoNumbers(a:Int,b:Int):Int =operators.subtract(a,b)
    fun multiplyTwoNumbers(a:Int,b:Int):Int = operators.multiply(a,b)
    fun divideTwoNumbers(a:Int,b:Int):Int =operators.divide(a,b)
}