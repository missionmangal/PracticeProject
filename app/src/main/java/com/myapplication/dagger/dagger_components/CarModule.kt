package com.myapplication.dagger.dagger_components

import com.myapplication.dagger.car.Car
import com.myapplication.dagger.car.Driver
import com.myapplication.dagger.car.Engine
import com.myapplication.dagger.car.Wheel
import dagger.Module
import dagger.Provides

@Module
class CarModule {

    companion object {
        @Provides
        @PerActivity
        fun provideCar(driver:Driver,engine: Engine,wheel:Wheel): Car {
            return Car(driver,engine,wheel)
        }
    }
}