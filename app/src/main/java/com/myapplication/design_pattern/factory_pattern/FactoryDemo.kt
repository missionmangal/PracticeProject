package com.myapplication.design_pattern.factory_pattern

fun main(arg1 :Array<String>) {

    var shape:Shape ?=null
//    Circle Object
    shape = ShapeFactory.Companion.getInsance("CIRCLE")
    shape?.draw()
//    Rectanlge Object
    shape = ShapeFactory.Companion.getInsance("RECTANGLE")
    shape?.draw()
//    Square Object
    shape = ShapeFactory.Companion.getInsance("SQUARE")
    shape?.draw()

}

class FactoryDemo {

}