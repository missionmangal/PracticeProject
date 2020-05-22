package com.myapplication.dagger.dagger_components

import com.base.MyApplication
import com.myapplication.dagger.car.Driver
import com.myapplication.dagger.car.DriverModule
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DriverModule::class])
interface AppComponent {

//    fun getDriver():Driver

//    fun getCarcomponent(engine:DisealModule):CarComponent
//fun getCarcomponentBuilder():CarComponent.Builder
fun getCarcomponentFactory():CarComponent.Factory


    @Component.Factory
    interface Factory{
        fun create(driverModule: DriverModule):AppComponent
    }
}