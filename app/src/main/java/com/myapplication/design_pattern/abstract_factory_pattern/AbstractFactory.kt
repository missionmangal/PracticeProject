package com.myapplication.design_pattern.abstract_factory_pattern

import com.myapplication.design_pattern.abstract_factory_pattern.color.Color
import com.myapplication.design_pattern.abstract_factory_pattern.shape.Shape

abstract class AbstractFactory {
    abstract fun getShape(type :String):Shape?
    abstract fun getColor(type : String):Color?

}