package com.myapplication.design_pattern.observer_pattern

class OctalObserver (subject: Subject): Observer() {

    init {
        this.subject = subject
        this.subject?.attatch(this)
    }
    override fun update() {
        println("Octal string :" + Integer.toOctalString(this.subject?.getState()?:0))
    }

}