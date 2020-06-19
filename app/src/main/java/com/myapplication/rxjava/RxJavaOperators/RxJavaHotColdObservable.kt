package com.myapplication.rxjava.RxJavaOperators

import io.reactivex.Observable
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

fun main() {
    var rxjava = RxJavaHotColdObservable()
    rxjava.hotObservable()
}

class RxJavaHotColdObservable {

    fun coldObservable(){
        var observable = Observable.intervalRange(21,10,0,1,TimeUnit.SECONDS)

        observable.subscribe({System.out.println(it)})
        observable.subscribe({System.out.println(it)})
        runBlocking { delay(10000) }
    }

    fun hotObservable(){
        var hotObservable = Observable.intervalRange(31,10,0,1,TimeUnit.SECONDS)
                .publish()
        hotObservable.subscribe({System.out.println("Observer 1:: " + it)})
        hotObservable.connect()
        GlobalScope.launch {
            delay(3000)
            hotObservable.subscribe{System.out.println("Observer 2:: " + it)}
        }
        runBlocking { delay(12000) }
    }
}