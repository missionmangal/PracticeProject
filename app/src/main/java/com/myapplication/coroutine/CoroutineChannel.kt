package com.myapplication.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit>{
   val channel = Channel<String>()

    launch{
        for(i in 1..5) {
            log("A Sending $i")
            channel.send("A${i}")
        }
        log("A Done")

    }

    launch {
        for(i in 1..5){
            log("B Sending B$i")
            channel.send("B${i}")
        }
        log("B Done")
    }

    launch{
        repeat(10){
            val x = channel.receive()
            log("Received ${it}:: $x")
        }
//        channel.close()
    }

}

fun log(message:Any?){
    println("[${Thread.currentThread().name}]  $message"  )
}

class CoroutineChannel {


}