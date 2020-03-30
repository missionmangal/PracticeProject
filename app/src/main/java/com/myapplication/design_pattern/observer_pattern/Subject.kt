package com.myapplication.design_pattern.observer_pattern

class Subject {
    private var observers : ArrayList<Observer> = ArrayList()
    private var state:Int=0
    public fun getState() = state
    public fun setState(value:Int)  {state = value
        notifyAllObserver()
    }

    fun attatch( observer: Observer){
        observers.add(observer)
    }
    fun detattch(observer: Observer) = observers.remove(observer)

    fun notifyAllObserver(){
        observers.forEach{it.update()}
    }
}