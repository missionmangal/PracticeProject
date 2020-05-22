package com.myapplication.dagger.dagger_components

import com.myapplication.dagger.car.DisealEngine
import com.myapplication.dagger.car.Engine
import dagger.Module
import dagger.Provides

@Module
class DisealModule( horsePower:Int) {

    var horsePower:Int
    init {
        this.horsePower =horsePower
    }

    @Provides
    fun provideHorsePower():Int{
        return horsePower
    }
    @Provides
    fun provideDisealEngine(engine: DisealEngine):Engine{
        return engine
    }


}