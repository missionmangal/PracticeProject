package com.myapplication.dagger.car

import com.myapplication.dagger.dagger_components.PerActivity
import javax.inject.Inject
import javax.inject.Singleton


class Car  constructor(var driver:Driver,var engine:Engine,var wheel:Wheel) {

//    @Inject
////    lateinit var driver:Driver
////    @Inject
////    lateinit var engine:Engine
////    @Inject
////    lateinit var wheel:Wheel

    init {
        println("Here is car")
    }

    fun drive(){
        engine.start()
        println("Driving a car")
    }
}