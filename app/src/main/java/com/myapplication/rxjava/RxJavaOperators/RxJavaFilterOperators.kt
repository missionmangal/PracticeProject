package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import java.util.function.Predicate

fun main() {
    var filterOperators = RxJavaFilterOperators()
//    filterOperators.debounceOperator()
//    filterOperators.distinctOperator()
//    filterOperators.elementAtOperator()
//    filterOperators.filterOperator()
//    filterOperators.firstOperator()
//    filterOperators.iignoreElementOperator()
//    filterOperators.lastOperator()
//    filterOperators.sampleOperator()
//    filterOperators.skipOperator()
    filterOperators.takeOperator()
}

class RxJavaFilterOperators {

    var arr = arrayOf("Hi", "Hey", "Hello", "Hello", "Hey", "Hi2")
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

    //    ##############################################################
//    ***************************Last Operator Start*********************
    fun lastOperator() {
        Observable.fromIterable(ArrayList<String>())
                .last("Last Element")
//                .lastOrError()
                .subscribe{ it,err->
                    if(err==null)
                        System.out.println(it)
                    else
                        System.out.println(err.toString())
                }
                }

//    ***************************Lastt Operator End **********************


    //    ##############################################################
//    ***************************Sample Operator Start*********************
    fun sampleOperator() {
        Observable.intervalRange(10,10,0,1,TimeUnit.SECONDS)
                .sample(1500,TimeUnit.MILLISECONDS)
                .subscribe(
                        {System.out.println(it)},
                        {System.out.println(it.message)},
                        {System.out.println("Completed")}
                )
            runBlocking { delay(10000) }

    }


//    ***************************Sample Operator End **********************


    //    ##############################################################
//    ***************************Skip Operator Start*********************
    fun skipOperator() {
        Observable.intervalRange(11,10,0,1,TimeUnit.SECONDS)
//                .skip(1500,TimeUnit.MILLISECONDS)
//                .skip(4)
                .skipLast(4)
                .subscribe(
                        {System.out.println(it)},
                        {System.out.println(it.message)},
                        {System.out.println("Completed")}
                )
            runBlocking { delay(10000) }

    }


//    ***************************Skip Operator End **********************

    //    ##############################################################
//    ***************************Take Operator Start*********************
    fun takeOperator() {
        Observable.intervalRange(11,10,0,1,TimeUnit.SECONDS)
//                .take(1500,TimeUnit.MILLISECONDS)
//                .take(4)
//                .takeLast(4)
//                .takeWhile { it<18 }
                /*.takeWhile(object : io.reactivex.functions.Predicate<Long>{
                    override fun test(t: Long): Boolean {
                        return t<18
                    }
                })*/
                .takeUntil(object :io.reactivex.functions.Predicate<Long>{
                    override fun test(t: Long): Boolean {
                        return t>15

                    }
                })
                .subscribe(
                        {System.out.println(it)},
                        {System.out.println(it.message)},
                        {System.out.println("Completed")}
                )
            runBlocking { delay(10000) }

    }


//    ***************************Skip Operator End **********************

}