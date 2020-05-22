package com.myapplication.dagger.car

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriverModule (var name:String){

//    companion object {
        @Singleton
        @Provides
        fun provideDriver(): Driver {
            return Driver(name)
        }
//    }
}