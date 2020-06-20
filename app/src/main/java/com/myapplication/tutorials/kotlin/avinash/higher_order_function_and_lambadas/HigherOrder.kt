package com.myapplication.tutorials.kotlin.avinash.higher_order_function_and_lambadas

fun main(){

    /*---------------------------------LAMBADAS SYNTAX--------------------------------------------*/
    val square : (Int) -> Int = { value -> value * value }
    /*---------------------------------LAMBADAS SYNTAX--------------------------------------------*/
    System.out.println(square(3))

    /*---------------------------------LAMBADAS EXAMPLES--------------------------------------------*/
    val add : (Int,Int) -> Int = {
        a,b -> a+b
    }
    val mul : (Int, Int) -> Int = {
        a,b -> a*b
    }
    val percentage : (Double, Double) -> Double = {
        amount,percent -> (amount/100.0f) * percent
    }
    val minus : (Double,Double) -> Double = {
        a,b -> a-b
    }
    /*---------------------------------LAMBADAS EXAMPLES--------------------------------------------*/
    System.out.println(add(5,5))
    System.out.println(mul(5,5))

    var salary = 40000.00
    var percent = 33.75

    System.out.println("33% of my salary is Rs. ".plus(percentage(salary,percent)))
    System.out.println("Getting Cash in hand is about Rs. ".plus(minus(salary,percentage(salary,percent))))

}