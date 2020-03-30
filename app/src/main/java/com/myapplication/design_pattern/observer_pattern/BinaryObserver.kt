package com.myapplication.design_pattern.observer_pattern

class BinaryObserver( subject: Subject) : Observer() {

    init {
        this.subject = subject
        this?.subject?.attatch(this)
    }
    override fun update() {
        println("Binary string :" + Integer.toBinaryString(this.subject?.getState()?:0))
    }
}