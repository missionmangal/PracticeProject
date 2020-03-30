package com.myapplication.design_pattern.abstract_factory_pattern

import com.myapplication.design_pattern.abstract_factory_pattern.color.ColorFactory
import com.myapplication.design_pattern.abstract_factory_pattern.shape.ShapeFactory


class FactoryProducer {
    fun getFactoryInstance(type:String):AbstractFactory?{
        when(type){
            "SHAPE" -> return ShapeFactory()
            "COLOR" -> return ColorFactory()
        }
        return null
    }
}