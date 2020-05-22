package com.myapplication.dagger.car

import javax.inject.Inject
import javax.inject.Named

class PetrolEngine @Inject constructor(@Named("horse_power") var  horsePower:Int,@Named("capacity") var capacity:Int) : Engine {

    override fun start() {
        println("Petrol engine started :: horsepower = ${horsePower} capacity = ${capacity}")
    }
}