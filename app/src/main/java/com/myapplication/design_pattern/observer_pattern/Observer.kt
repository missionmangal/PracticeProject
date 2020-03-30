package com.myapplication.design_pattern.observer_pattern


abstract class Observer {

    var subject : Subject?=null
    abstract fun update()
}