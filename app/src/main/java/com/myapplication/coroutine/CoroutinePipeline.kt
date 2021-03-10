package com.myapplication.coroutine
/*
* Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

// This file was automatically generated from channels.md by Knit tool. Do not edit.

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce


fun main() = runBlocking {
    val numbers = produceNumber() // produces integers from 1 and on
    val squares = com.myapplication.coroutine.square(numbers) // squares integers
    repeat(5) {
        println(squares.receive()) // print first five
    }
    println("Done!") // we are done
    coroutineContext.cancelChildren() // cancel children coroutines
}


fun CoroutineScope.produceNumbers() =
        produce<Int> {
            var x = 1
            while (true) send(x++) // infinite stream of integers starting from 1
        }


fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) {
        send(x * x * x)
        delay(1000)
    }
}

fun square (numbers : ReceiveChannel<Int>): ReceiveChannel<Int> {
    return MainScope().produce(Dispatchers.IO) {
        for( x in numbers)
        {
            send(x * 2)
            delay(1000)
        }
    }
}


fun produceNumber()  : ReceiveChannel<Int>{
    val result = MainScope().produce(Dispatchers.IO) {
        var x = 1
        while (true)
            send(x++)
    }
    return result
}

class CoroutinePipeline {


}