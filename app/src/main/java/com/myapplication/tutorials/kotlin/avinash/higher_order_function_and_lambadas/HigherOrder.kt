package com.myapplication.tutorials.kotlin.avinash.higher_order_function_and_lambadas

fun main() {


    /*---------------------------------LAMBADAS SYNTAX--------------------------------------------*/
    val syntax: (Int) -> Int = { value -> value * value }
    val syntax2 = { value: Int -> value * value }
//    System.out.println(syntax2(3))
    /*---------------------------------LAMBADAS SYNTAX--------------------------------------------*/



    /*---------------------------------LAMBADAS DECLARATION--------------------------------------------*/

    fun addition(a: Double, b: Double): Double {
        return a + b;
    }

    val add: (Double, Double) -> Double = { a, b -> a + b }
    val add2 = { a: Double, b: Double -> a + b }

    val mul: (Double, Double) -> Double = { a, b -> a * b }
    val mul2 = { a: Double, b: Double -> a * b }

    val percentage: (Double, Double) -> Double = { amount, percent ->
        (amount / 100.0f) * percent
    }
    val percentage2 = { amount: Double, percent: Double ->
        (amount / 100.0f) * percent
    }


    val minus: (Double, Double) -> Double = { a, b ->
        a - b
    }
    val minus2 = { a: Double, b: Double ->
        a - b
    }

    var lambada = { System.out.println("Hello world") }
    var lambada2: () -> Unit = { System.out.println("Hello world") }

    /*---------------------------------LAMBADAS DECLARATION--------------------------------------------*/



    /*---------------------------------LAMBADAS CALLING--------------------------------------------*/
//    System.out.println(add2(5.0,5.0))
//    System.out.println(mul2(5.0,5.0))

    var salary = 40000.00
    var percent = 33.75

//    System.out.println("33% of my salary is Rs. ".plus(percentage2(salary,percent)))
//    System.out.println("Getting Cash in hand is about Rs. ".plus(minus2(salary,percentage2(salary,percent))))
    /*---------------------------------LAMBADAS CALLING--------------------------------------------*/



    /*---------------------------------Higher Order Function EXAMPLES--------------------------------------------*/
    fun passMeFunction(info:String, someOperation: (Double, Double) -> Double) {
        // I can take function
        // do something here
        // execute the function
        val result = someOperation(5.0, 5.0)
        System.out.println(info.plus(" - ").plus(result))
    }

    /*-------------------Now we can pass any function/lambada which meets the function syntax----------------------------*/
    passMeFunction("Pass Lambada body",{ a, b -> a + b })
    passMeFunction("Pass Lambada out of the paranthesis"){
        a, b -> a + b
    }
    passMeFunction("Addition",add2)
    passMeFunction("Addition"){x,y -> x+y}
    passMeFunction("Multiplication",mul2)
    passMeFunction("Substraction",minus2)
    passMeFunction("Percentage",percentage2)
    /*---------------------------------Higher Order Function EXAMPLES--------------------------------------------*/
}

