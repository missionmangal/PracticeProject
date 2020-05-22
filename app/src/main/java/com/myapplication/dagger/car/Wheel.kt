package com.myapplication.dagger.car

import javax.inject.Inject

class Wheel  (var rim:Rim,var tyre:Tyres) {

    init {
        println("Here is Wheel")
    }
}