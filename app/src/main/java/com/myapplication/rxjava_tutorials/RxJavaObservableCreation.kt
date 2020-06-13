package com.myapplication.rxjava_tutorials

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.sql.Time
import java.util.concurrent.TimeUnit

fun main() {
    var rxJava  = RxJavaObservableCreation()
//    rxJava.createUsingJust()
//    rxJava.createUsingInterval()
//    rxJava.createUsingRange()
    rxJava.createUsingCreate()
}

class RxJavaObservableCreation {

    var arr = arrayOf("Hi", "Hey","Hello","Bonzo");

    fun getArray(): Array<String> {
      return arr
    }
// #############################################################
// **********************Just Start***************************
    fun createUsingJust(){
    var  dispose  :Disposable ?=null
        Observable.just(getArray())
                .subscribe(object : Observer<Array<String>>{
                    override fun onComplete() {
                        System.out.println("On Complete ::")
                    }

                    override fun onSubscribe(d: Disposable) {
                        dispose = d;
                        System.out.println("On Subscribe ::")
                    }

                    override fun onNext(t: Array<String>) {

                        t.forEach { System.out.println(it) }
                    }

                    override fun onError(e: Throwable) {
                        System.out.println("On Error ::")
                    }
                })
    }

    // **********************Just End***************************



// #############################################################
// **********************Interval Start***************************
    fun createUsingInterval(){
        var compositeDisposable:CompositeDisposable = CompositeDisposable()

        var disposable = Observable.interval(1,TimeUnit.SECONDS)
                .subscribe(
                        { System.out.println(it)},
                        {err ->System.out.println(err.message)},
                        {System.out.println("OnComplete")}
                        )

        compositeDisposable.add(disposable)
        runBlocking {
            delay(5000)
        }

        if(!disposable.isDisposed){
//            disposable.dispose()
            compositeDisposable.clear()

            System.out.println(" Task Disposed")
        }
        runBlocking {
            delay(2000)
        }


    }

    // **********************Interval End***************************

// #############################################################
// **********************Range Start***************************
    fun createUsingRange(){
        Observable.range(11,10)
                .subscribe(
                        {System.out.println(it)},
                        {System.out.println(it)},
                        {System.out.println("On Complete")}
                )
    }

    // **********************Range End***************************


// #############################################################
// **********************Create Start***************************
    fun createUsingCreate(){

                getObservable().subscribe(
                        {System.out.println(it)},
                        {err->System.out.println(err.message)},
                        {System.out.println("On Complete")}
                )
    }

        fun getObservable():Observable<String>
        {
            return  Observable.create( object : ObservableOnSubscribe<String>{
                override fun subscribe(emitter: ObservableEmitter<String>) {
                    var newArray = getArray()
                    newArray.forEach {
                        runBlocking { delay(1000)
                            System.out.println("Calling On next = $it")
                            emitter.onNext(it)
                            if(it.equals("Hello")){
                                emitter.onError(Throwable("Hello Error :::: "))
                            }
                        }
                    }
                    emitter.onComplete()
                }
            })
        }
    // **********************Create End***************************






}