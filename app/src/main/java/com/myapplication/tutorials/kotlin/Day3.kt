package com.myapplication.tutorials.kotlin

fun main() {

    var day3 = Day3()
    day3.addThreeNumber(5,    { x,y->
        System.out.println(x+y)
        var result =0;
        if(x+y ==16)
            result = x+y
        else{
            result = x*y
        }
        result
    }
    )
}

class Day3 {


    var add : (Int,Int)->Int = { x,y->
        System.out.println(x+y)
        var result =0;
        if(x+y ==16)
            result = x+y
        else{
            result = x*y
        }
        result
    }


    fun addTwoNumber(x:Int,y:Int){
        var result =  add(x,y)
        System.out.println(result)
    }
//*************************** Higher Order fun as Param*******************************
    fun addThreeNumber(x:Int, z:(Int,Int)->Int ) : Int {

        System.out.println( x+z(10,20))
        return x+z(10,20)
    }




//*************************** Higher Order fun as return function*******************************
fun mulThreeNumber(x:Int, z:(Int,Int)->Int ) : (Int)->Unit {
    var result =x+z(10,20)
    System.out.println( x+z(10,20))
    var printFun : (Int)->Unit  = {System.out.println(it)}

    return printFun
}
}