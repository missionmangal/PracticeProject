package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    var filterOperators = RxJavaFilterOperators()
//    filterOperators.debounceOperator()
//    filterOperators.distinctOperator()
//    filterOperators.elementAtOperator()
//    filterOperators.filterOperator()
//    filterOperators.firstOperator()
    filterOperators.iignoreElementOperator()
//    filterOperators.lastOperator()
}

class RxJavaFilterOperators {

    var arr = arrayOf("Hi", "Hey", "Hello", "Hello", "Hey", "Hi")
    var arr1 = arrayOf("Hi1", "Hey", "Hello", "Hello", "Hey0", "Hi0")
    fun getArray(): Array<String> {
        runBlocking {
            delay(1000)
        }
        return arr;
    }

    fun getArray1(): Array<String> {
        runBlocking {
            delay(1000)
        }
        return arr1;
    }

    //    ##############################################################
//    ***************************Debounce Start*********************
    fun debounceOperator() {
        Observable.intervalRange(12, 5, 0, 1, TimeUnit.SECONDS)
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn()
                .subscribe(
                        { System.out.println(it) },
                        { System.out.println(it.message) },
                        { System.out.println("Complete::") }
                )
        runBlocking {
            delay(6000)
        }
    }

//    ***************************Debounce End **********************

    //    ##############################################################
//    ***************************Distinct Start*********************
    fun distinctOperator() {
        Observable.fromIterable(getArray1().asList())
                .distinctUntilChanged { t1, t2 ->
                    System.out.println(t1 + " :: " + t2)
                    if (t2.length == t1.length)
                        return@distinctUntilChanged true
                    else
                        return@distinctUntilChanged false

                }
                .subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
//                .observeOn()
                .subscribe(
                        {
                            //                            System.out.println(it)
                        },
                        { System.out.println(it.message) },
                        { System.out.println("Complete::") }
                )

        runBlocking {
            delay(3000)
        }
    }

//    ***************************Debounce End **********************


    //    ##############################################################
//    ***************************Element At Start*********************
    fun elementAtOperator() {
        Observable.fromIterable(getArray1().asList())
//                .elementAt(15,"Wow")
                .elementAtOrError(14)
                .subscribe { t,err->

                 if(err!=null){
                     System.out.println("Error")
                     System.out.println(err)
                     }
                 else {
                     System.out.println("Result")
                     System.out.println(t)
                 }

                }
        Observable.fromIterable(getArray1().asList())
//                .elementAt(15,"Wow")
                .elementAt(14,"Wow")
                .subscribe { t,err->

                 if(err!=null){
                     System.out.println("Error")
                     System.out.println(err)
                     }
                 else {
                     System.out.println("Result")
                     System.out.println(t)
                 }

                }
        Observable.fromIterable(getArray1().asList())
//                .elementAt(15,"Wow")
                .elementAt(14)
                .subscribe( {
                     System.out.println(it)
                     } ,{System.out.println(it.message)},
                        {System.out.println("Completed::")})
        runBlocking {
            delay(3000)
        }
    }

//    ***************************Element At End **********************


    //    ##############################################################
//    ***************************Filter Start*********************
    fun filterOperator() {
        Observable.fromIterable(getArray1().asList())
                .filter { str->
                    if(str.contains("ll"))
                        return@filter true
                    else
                        return@filter false
                }
                .subscribe( {System.out.println(it)} ,
                        {System.out.println(it.message)},
                        {System.out.println("Completed::")})
                }

//    ***************************Filter End **********************

    //    ##############################################################
//    ***************************Filter Start*********************
    fun firstOperator() {
        Observable.fromIterable(getArray().asList())
                .first("Wow")
                .subscribe{ t->System.out.println(t)}
                }

//    ***************************Filter End **********************


    //    ##############################################################
//    ***************************iignoreElementOperator Start*********************
    fun iignoreElementOperator() {
        Observable.fromIterable(getArray().asList())
                .ignoreElements()
                .subscribe{ System.out.println("completed")}
                }

//    ***************************iignore Element Operator End **********************

}