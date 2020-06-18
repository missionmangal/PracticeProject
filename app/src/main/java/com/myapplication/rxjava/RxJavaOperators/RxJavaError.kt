package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import java.lang.IllegalArgumentException
import java.util.*

fun main() {
    var rxjava =RxJavaError()
//    rxjava.onErrorResumeNext()
    rxjava.onRetry()
}

class RxJavaError {

    var arr = arrayOf<String>("hi","hey","hello","bonzo")
//    **********************************onErrorResumeNext Operator Start*****************************
    fun onErrorResumeNext(){
        Observable.fromIterable(arr.asList())
                .map{ if (it.equals("hello"))
                    throw IllegalArgumentException("Illegal Argument Exception ---------")
                    it
                }
                .onErrorResumeNext( object :ObservableSource<String>{
                    override fun subscribe(observer: Observer<in String>) {
                        observer.onNext("wow")
                        observer.onComplete()
                    }
                })

                .subscribe(
                        {System.out.println(it)},
                        {System.out.println(it.localizedMessage)},
                        {System.out.println("OnCompleted")}
                )
    }

//    **********************************onErrorResumeNext Operator Start*****************************


//    **********************************onErrorReturn Operator Start*****************************
    fun onErrorReturn(){
        Observable.fromIterable(arr.asList())
                .map{ if (it.equals("hello"))
                    throw IllegalArgumentException("Illegal Argument Exception ---------")
                    it
                }

                .onErrorReturn { throwable:Throwable->
                    if(throwable is IllegalArgumentException){
                        return@onErrorReturn "Illegal Exception"
                    }
                    return@onErrorReturn "Error Return"
                }
                .subscribe(
                        {System.out.println(it)},
                        {System.out.println(it.localizedMessage)},
                        {System.out.println("OnCompleted")}
                )
    }

//    **********************************onErrorReturn Operator Start*****************************


//    **********************************onRetry Operator Start*****************************
    fun onRetry(){
        Observable.fromIterable(arr.asList())

                .map{
                    if (it.equals("bonzo"))
                    throw IllegalArgumentException("Illegal Argument Exception ---------")
                    it
                }
                .doOnNext{

                }
                .retry(4)
                .subscribe(
                        {

                            System.out.println(it)

                        },
                        {System.out.println(it.localizedMessage)},
                        {System.out.println("OnCompleted")}
                )
    }

//    **********************************onRetry Operator End*****************************
}