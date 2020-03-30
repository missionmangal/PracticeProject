package com.myapplication.design_pattern.abstract_factory_pattern

import com.myapplication.design_pattern.abstract_factory_pattern.shape.Shape

fun main() {
    var factoryProducer = FactoryProducer();
//    SHAPE
    var shapeFactory = factoryProducer.getFactoryInstance("SHAPE")

    var shape =shapeFactory?.getShape("RECTANGLE")
    shape?.draw()

     shape  = shapeFactory?.getShape("CIRCLE")
    shape?.draw()

    shape = shapeFactory?.getShape("SQUARE")
    shape?.draw()

//    Color
    var colorFactory = factoryProducer?.getFactoryInstance("COLOR")

    var color = colorFactory?.getColor("RED")
    color?.fill()

    color = colorFactory?.getColor("GREEN")
    color?.fill()

    color = colorFactory?.getColor("BLUE")
    color?.fill()

}
abstract class AbstractFactoryDemo {
}