package com.base

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.myapplication.dagger.car.DriverModule
import com.myapplication.dagger.dagger_components.AppComponent
import com.myapplication.dagger.dagger_components.CarComponent
import com.myapplication.dagger.dagger_components.DaggerAppComponent

class MyApplication : MultiDexApplication() {
    lateinit var appComponent:AppComponent
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        appComponent = DaggerAppComponent.factory().create(DriverModule("Vishnu soni"))
//        appComponent.inject(this)

    }
}