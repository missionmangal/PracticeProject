package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    var transform = RxJavaTransformOperators()
//    transform.bufferOperator()
//    transform.maoOperator()
//    transform.flatMaoOperator()
//    transform.scanOperator()
//    transform.groupByOperator()
    transform.windowOperator()
}

class RxJavaTransformOperators {
    var arrMovies = arrayOf("Iron Man", "Bat Man", "Avanger", "Captain America", "Civil War")

    fun printArray(array: Array<String>) {
        array.forEach { System.out.println(it) }
    }

    fun printList(array: List<String>) {
        array.forEach { System.out.println(it) }
    }

    private fun printArray(array: List<Array<String>>) {
        array.forEach { it.forEach { System.out.println(it) } }
    }
//    ###################################################################################################
//    **************************************Buffer Operator Start***************************************

    fun bufferOperator() {
        Observable.fromIterable(arrMovies.asList())
                .buffer(2)
                .subscribe({
                    System.out.println("\nBuffer Operator\n")
                    printList(it)
                },
                        {
                            System.out.println(it.message)
                        },
                        { System.out.println("Completed") })
    }


//    **************************************Buffer Operator End***************************************


//    ###################################################################################################
//    **************************************Map Operator Start***************************************

    fun maoOperator() {
        Observable.fromIterable(arrMovies.asList())
                .map{ it+"Hari Om"}
                .subscribe({

                    System.out.println(it)
                    },
                    {
                        System.out.println(it.message)
                    },
                    { System.out.println("Completed") })
    }


//    **************************************Map Operator End***************************************

//    ###################################################################################################
//    **************************************Flat Map Operator Start***************************************

    fun flatMaoOperator() {
//        Observable.fromIterable(arrMovies.asList())
        Observable.just(arrMovies.asList())
                .flatMap{
//                    return@flatMap Observable.just(it+" Krishna")
                    return@flatMap Observable.intervalRange(11,10,0,1,TimeUnit.SECONDS)
                }
                .subscribe({

                    System.out.println(it)
                    },
                    {
                        System.out.println(it.message)
                    },
                    { System.out.println("Completed") })
        runBlocking { delay(10000) }
    }


//    **************************************Flat Map Operator End***************************************


//    ###################################################################################################
//    **************************************Scan Operator Start***************************************

    fun scanOperator() {
        Observable.fromIterable(arrMovies.asList())
//        Observable.just(arrMovies.asList())
                .scan { t1: String, t2: String ->
                    System.out.println("t1 = "+t1)
                    System.out.println("t2 = "+t2)
                        t1+" "+t2
//                    "Hello"
                }
                .subscribe({
//                    System.out.println(it)
                    },
                    {
                        System.out.println(it.message)
                    },
                    { System.out.println("Completed") })
        runBlocking { delay(10000) }
    }


//    **************************************Scan Operator End***************************************

//    ###################################################################################################
//    **************************************groupBy Operator Start***************************************

    fun groupByOperator() {
        Observable.fromIterable(arrMovies.asList())
//        Observable.just(arrMovies.asList())
                .groupBy { it
                }
                .subscribe({
                    var key =it.key;
//                    var value = it.
                    System.out.println(it)
                    },
                    {
                        System.out.println(it.message)
                    },
                    { System.out.println("Completed") })
        runBlocking { delay(10000) }
    }


//    **************************************groupBy Operator End***************************************
//    ###################################################################################################
//    **************************************window Operator Start***************************************

    fun windowOperator() {
        Observable.fromIterable(arrMovies.asList())
//        Observable.just(arrMovies.asList())
                .window(2)
                .flatMap {
//                    it = Observable.just("Hello")
                    return@flatMap it
                }
                .subscribe({
                    System.out.println("*************Window**************")
                    System.out.println(it)
                    },
                    {
                        System.out.println(it.message)
                    },
                    { System.out.println("Completed") })
        runBlocking { delay(10000) }
    }


//    **************************************window Operator End***************************************


}