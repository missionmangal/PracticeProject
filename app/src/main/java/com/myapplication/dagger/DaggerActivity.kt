package com.myapplication.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.base.MyApplication
import com.myapplication.R
import com.myapplication.dagger.car.Car
import com.myapplication.dagger.car.Engine
import com.myapplication.dagger.car.Wheel
import com.myapplication.dagger.dagger_components.DisealModule
import javax.inject.Inject
import kotlin.random.Random

class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var car1 :Car
    @Inject
    lateinit var car2 :Car
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)
        run()
    }

    fun run(){
        var appComponent = (application as MyApplication).appComponent
        var carComponent = appComponent.getCarcomponentFactory()
                .create(Random.nextInt(100), Random.nextInt(100,200))

        carComponent.inject(this)
        car1.drive()
        car2.drive()

        println("Car ${car1} is driven by driver ${car1.driver.name}")
        println("Car ${car2} is driven by driver ${car2.driver.name}")
    }
}
