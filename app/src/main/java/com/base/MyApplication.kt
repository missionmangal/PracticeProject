package com.base

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.myapplication.dagger.car.DriverModule
import com.myapplication.dagger.dagger_components.AppComponent
import com.myapplication.dagger.dagger_components.DaggerAppComponent
import com.myapplication.leakedActivity.LeakedActivity
import java.lang.ref.WeakReference

class MyApplication : MultiDexApplication() {
    lateinit var appComponent:AppComponent
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        appComponent = DaggerAppComponent.factory().create(DriverModule("Vishnu soni"))
//        appComponent.inject(this)

    }
//    Testing leaked activity
    var  leakedActivity : WeakReference<LeakedActivity>?=null
//    var  leakedActivity : LeakedActivity?=null

    fun saveActivityInstance(leakedActivity: LeakedActivity) {
//        this.leakedActivity = leakedActivity
        this.leakedActivity = WeakReference(leakedActivity)
    }
}