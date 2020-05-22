package com.myapplication.dagger

import com.myapplication.dagger.car.Car
import com.myapplication.dagger.dagger_components.DisealModule
import javax.inject.Inject
import kotlin.random.Random

fun main() {

    var demo = DaggerDemo()
    demo.run()

}
class DaggerDemo {

    @Inject
    lateinit var car1 :Car
    @Inject
    lateinit var car2 :Car
    fun run(){
//        var appComponent = (appli)
        /*var carComponent = DaggerCarComponent
                .builder()
                .horsePower(Random.nextInt(100))
                .capacity(Random.nextInt(150))
                .build()
        carComponent.inject(this)*/
        car1.drive()
        car2.drive()

        println("Car ${car1} is driven by driver ${car1.driver}")
        println("Car ${car2} is driven by driver ${car2.driver}")
    }
}