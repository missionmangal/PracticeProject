package com.myapplication.kotlin.delegation

class DelegationDemo(b: Base) : Base by b

interface Base {
    fun printMessage(msg: String)
}

class BaseImpl : Base {
    override fun printMessage(msg: String) {
        println(msg)
    }
}

fun main() {
    val b = BaseImpl()
    val delegationDemo = DelegationDemo(b)
    delegationDemo.printMessage("Hello Vishnu")
}
