package com.myapplication.rxjava.CreateOperators

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit


fun main() {

    var test = RxJavaCreateOperators();
//    test.observableUsingJust()
//    test.observableUsingCreate()
//    test.observableUsingDefer()
//    test.observableUsingEmptyNeverThrow()
//    test.observableUsingInterval()
//    test.observableUsingRange()
    test.observableUsingTimer()
}

class RxJavaCreateOperators {
    var arr = arrayOf<String>("Hi", "Hey", "Hello", "Bonzo")
    var arr1 = arrayOf<String>("Hi1", "Hey1", "Hello1", "Bonzo1")

    var observer1 = object : Observer<String> {
        override fun onComplete() {
            System.out.println("Completed :: ")
        }

        override fun onSubscribe(d: Disposable) {

            System.out.println("OnSubscribe :: ")
        }

        override fun onNext(t: String) {

            System.out.println("OnNext :: $t ")
        }

        override fun onError(e: Throwable) {

            System.out.println("OnError:: ${e.message} ")
        }
    }
    var observer2 = object : Observer<String> {
        override fun onComplete() {
            System.out.println("Completed :: ")
        }

        override fun onSubscribe(d: Disposable) {

            System.out.println("OnSubscribe :: ")
        }

        override fun onNext(t: String) {

            System.out.println("OnNext :: $t ")
        }

        override fun onError(e: Throwable) {

            System.out.println("OnError:: ${e.message} ")
        }
    }

    //    ********************JUST START***************************************
    fun observableUsingJust() {
        var observable = Observable.just(arr)
        observable.subscribe({
            it.forEach { System.out.println(it) }
        }, { System.out.println(it.message) },
                { System.out.println("Completed") })
        runBlocking {
            delay(2000)
        }
        arr = arr1;
        observable.subscribe({
            it.forEach { System.out.println(it) }
        }, { System.out.println(it.message) },
                { System.out.println("Completed") })

    }

    //    ********************JUST END***************************************
//#####################################################################################################
//    ********************CREATE START***************************************
    fun observableUsingCreate() {
        var observable = Observable.create { emitter: ObservableEmitter<String> ->
            arr.forEach {
                if (it.equals("Bonzo")) {
                    emitter.onError(Throwable("Error Spanish"))
                }
                emitter.onNext(it)
            }
            emitter.onComplete()
        }

        observable.subscribeWith(observer1)
        runBlocking {
            delay(2000)
        }

        arr = arr1;
        observable.subscribeWith(observer2)

    }
//    ********************CREATE END***************************************

//#####################################################################################################

    //    ********************Defer START*************************************
    fun observableUsingDefer() {
        var observable = Observable.defer<Array<String>> {
            Observable.just(arr)
        }

        observable.subscribe({
            it.forEach { System.out.println(it) }
        }, { System.out.println(it.message) },
                { System.out.println("Completed") })
        runBlocking {
            delay(2000)
        }
        arr = arr1;
        observable.subscribe({
            it.forEach { System.out.println(it) }
        }, { System.out.println(it.message) },
                { System.out.println("Completed") })

    }

//    ********************Defer END***************************************

//#####################################################################################################


    //    ******************** EmptyNeverThrow START*************************************
    fun observableUsingEmptyNeverThrow() {
        System.out.println("Empty Observable")
        Observable.empty<String>().subscribe(observer1)

        System.out.println("Never Observable")
        Observable.never<String>().subscribe(observer1)
//       Observable.T<String>().subscribe(observer1)

    }

//    ******************** EmptyNeverThrow END***************************************

//#####################################################################################################


    //    ******************** FROM START*************************************
    fun observableUsingFrom() {
        System.out.println("FromIterable Observable")
        Observable.fromIterable(arr.asList())
                .subscribe({ str -> System.out.println(str) },
                        { error -> System.out.println(error.message) },
                        { System.out.println("onComplete") })


        System.out.println("FromArray Observable")
        Observable.fromArray(arr).subscribe(
                { it.forEach { System.out.println(it) } },
                { error -> System.out.println(error.message) },
                { System.out.println("onComplete") })

        System.out.println("FromCallable Observable")
        Observable.fromCallable(object : Callable<Array<String>> {
            override fun call(): Array<String> {
                return arr
            }
        }).subscribe(
                { it.forEach { System.out.println(it) } },
                { error -> System.out.println(error.message) },
                { System.out.println("onComplete") })


        System.out.println("FromFuture Observable")
        Observable.fromFuture<Any>(FutureTask<Any>(io.reactivex.internal.functions.Functions.EMPTY_RUNNABLE, "Hello"), 1, TimeUnit.SECONDS)
                .subscribe({ System.out.println(it) }
                        , { System.out.println(it.message) })

        System.out.println("FromPublisher Observable")
        Observable.fromPublisher(object : Publisher<Array<String>> {
            override fun subscribe(s: Subscriber<in Array<String>>?) {
                s?.onNext(arr)
                s?.onComplete()
            }
        }).subscribe(
                { it.forEach { System.out.println(it) } },
                { System.out.println(it.message) },
                { System.out.println("OnCompleted:: ") }
        )
    }

//    ******************** FROM END***************************************

//#####################################################################################################


    //    ******************** Interval START ***************************************
    fun observableUsingInterval() {
        Observable.interval(1L, TimeUnit.SECONDS)
                .take(10)
                .subscribe({ System.out.println(it) }
                        , { System.out.println(it.message) },
                        { System.out.println("Completed::") })
        runBlocking {
            delay(5000L)
        }

        Observable.intervalRange(11,20,0,1,TimeUnit.SECONDS)

                .subscribe({ System.out.println(it) }
                        , { System.out.println(it.message) },
                        { System.out.println("Completed::") })
        runBlocking { delay(5000L) }
    }
    //    ******************** Interval END ***************************************

//#####################################################################################################


    //    ******************** Interval START ***************************************
    fun observableUsingRange() {

        Observable.range(1,10)

                .subscribe({ System.out.println(it) }
                        , { System.out.println(it.message) },
                        { System.out.println("Completed::") })
        runBlocking { delay(5000L) }


    }
    //    ******************** Interval END ***************************************


//#####################################################################################################


    //    ******************** Timer START ***************************************
    fun observableUsingTimer() {
        Observable.timer(3,TimeUnit.SECONDS)

                .subscribe({ System.out.println(it) }
                        , { System.out.println(it.message) },
                        { System.out.println("Completed::") })
        runBlocking { delay(5000L) }
    }
    //    ******************** timer END ***************************************
}