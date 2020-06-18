package com.myapplication.tutorials.kotlin

import com.myapplication.setImage

fun main() {

    var kotlinDay1 = KotlinDay1(15,24)
    kotlinDay1.extensionFunction(25,10,{x,y->
        System.out.println(x*y)
        x*y
    }
    )

    kotlinDay1.multiplyUsingInterface(5,10,object :KotlinDay1.Calculator{
        override fun multiply(x: Int, y: Int): Int {
            System.out.println(x*y)
            return x*y
        }
    })
}

class KotlinDay1(a:Int = 14 , dd:Int  ){

    constructor(a:Int,b:Int,c:Int) :this(dd=  b){

    }

    var add :(a:Int,b:Int)->Int = {x,y ->
        System.out.println(x+y)
        x+y
    }


    fun addition(a:Int,b:Int):Int{
        System.out.println(a+b)
        return a+b
    }

    fun add(){
//        var c = a+5
    }

//    lateinit var multiplys:(x:Int,y:Int)
//    ***********************************Extension Function Start*************************************
        fun extensionFunction(a:Int , b:Int , multiply:(x:Int,y:Int)->Int):Int{
            var mul = multiply(5,10)
            return a+b+mul
        }


//    ***********************************Extension Function End***************************************

//    *************************************Interface Function Start **********************************

        interface  Calculator{
            fun multiply(x:Int, y:Int):Int
        }

        fun multiplyUsingInterface(x:Int,y:Int,cal:Calculator):Int{
            var mul = cal.multiply(x,y)
            return mul;
        }


//    *************************************Interface Function End **********************************
}