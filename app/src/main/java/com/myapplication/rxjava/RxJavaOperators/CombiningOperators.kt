package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.NullPointerException
import java.util.*
import java.util.concurrent.TimeUnit

fun main() {

    var combiningOperators = CombiningOperators()
//    combiningOperators.mergeOperator()
//    combiningOperators.zipOperator()
//    combiningOperators.joinOperator()
//    combiningOperators.switchOperator()
//    combiningOperators.flatMapOperator()
//    combiningOperators.startWithOperator()
    combiningOperators.retryLatestOperator()
}
class CombiningOperators {
    var arr = arrayOf("Hi", "Hey", "Hello", "Hey", "Hi")
    var arr1 = arrayOf("Hi2", "Hey2", "Hello2",  "Hey2", "Hi2")
    var observable1 = Observable.intervalRange(0,arr.size.toLong(),0,1,TimeUnit.SECONDS)
            .flatMap { Observable.just(arr.get(it.toInt())) }
    var observable2 = Observable.intervalRange(0,arr.size.toLong(),0,1500,TimeUnit.MILLISECONDS)
            .flatMap { Observable.just(arr1.get(it.toInt())) }


//    ***********************************Merge Operator Start*************************
        fun mergeOperator(){
            observable1.mergeWith(observable2)
                    .subscribe(
                            {System.out.println(it)},
                            {System.out.println(it)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************Merge Operator End*************************

//    ***********************************zip Operator Start*************************
        fun zipOperator(){
            observable1.zipWith(observable2 ,BiFunction { t1:String, t2:String -> t1+t2 })
                    .subscribe(
                            {System.out.println(it)},
                            {System.out.println(it)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************zip Operator Start*************************

//    ***********************************Switch Operator Start*************************
        fun switchOperator(){
            observable1.switchMap { Observable.just(it) }
                    .subscribe(
                            {System.out.println(it)},
                            {System.out.println(it)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************Switch Operator Start*************************


//    ***********************************Join Operator Start*************************
       fun joinOperator(){
            observable1.join( observable2, Function { it:String-> Observable.just(it)},
                    Function { it:String-> Observable.just(it)  }, BiFunction{ t1:String, t2:String -> t1+t2 })
                    .subscribe(
                            {System.out.println(it)},
                            {System.out.println(it)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************Join Operator Start*************************

//    ***********************************FlatMap Operator Start*************************
       fun flatMapOperator(){
            observable1.flatMap{
                Observable.just(it)}
                    .subscribe(
                            {System.out.println(it)},
                            {System.out.println(it)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************FlatMap Operator Start*************************

//    ***********************************StartWith Operator Start*************************
       fun startWithOperator(){
            observable1.startWith("Wow")
                    .subscribe(
                            {System.out.println(it)},
                            {System.out.println(it)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************StartWith Operator Start*************************


//    ***********************************Retry  Operator Start*************************
       fun retryLatestOperator(){
            observable1
//                    .runCatching {  }
//                    .retry(2000)
                    .onErrorReturn { "Wow we get an error" }
                    .doOnError({if(it is NullPointerException){
                            System.out.println("Wow we get an error")
                    } })
                    .subscribe(
                            {
                                if(it.equals("Hello")){
                                    throw NullPointerException("Null Pointer Exception")
                                }
                                System.out.println(it)
                                },
                            {System.out.println(it.message)},
                            {System.out.println("Completed")})
            runBlocking { delay(10000) }
        }

//    ***********************************CombineLatest  Operator Start*************************
}