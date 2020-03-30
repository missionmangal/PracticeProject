package com.myapplication.design_pattern.observer_pattern

class HexaObserver (subject: Subject) : Observer(){

    init {
        this.subject = subject
        this.subject?.attatch(this)
    }
    override fun update() {
        println("Hexa string :" + Integer.toHexString(this.subject?.getState()?:0))
    }

}