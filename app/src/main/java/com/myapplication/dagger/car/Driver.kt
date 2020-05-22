package com.myapplication.dagger.car

import javax.inject.Inject
import javax.inject.Singleton


class Driver constructor(var name:String){


    init {
        println("Here is driver")
    }
}

