package com.myapplication.dagger.car

import javax.inject.Inject

class DisealEngine @Inject constructor(var horsePower: Int) :Engine {
    override fun start() {
        println("Diseal engine started: ${horsePower}")
    }
}