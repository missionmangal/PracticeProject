package com.myapplication.problem_solving.simplePrograms

fun main() {

    val fibonaciSeries = FibonaciSeries()
    fibonaciSeries.createFibonaciSeries(0,1,1,10)
}

class FibonaciSeries {

    //    create fibonaci series using recursion
    //    n is index till then we are to create fibonaci series
    fun createFibonaciSeries(x: Int, y: Int, count: Int, n: Int) {
        if(count <= n){
        print("$x ")
        val b = x+y
        val a = b - x

            createFibonaciSeries(a,b, count+1, n)
        }
    }
}