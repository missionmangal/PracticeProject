package com.myapplication.dagger.dagger_components

import com.myapplication.dagger.car.Engine
import com.myapplication.dagger.car.Rim
import com.myapplication.dagger.car.Tyres
import com.myapplication.dagger.car.Wheel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class WheelModule {

    companion object {
        @Provides
        fun provideRim(): Rim {
            return Rim()
        }

        @Provides
        fun provideTyre(): Tyres {
            return Tyres()
        }

        @Provides
        fun provideWheels(rim: Rim, tyres: Tyres): Wheel {
            return Wheel(rim, tyres)
        }


    }
}