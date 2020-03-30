package com.myapplication.design_pattern.abstract_factory_pattern.shape

import com.myapplication.design_pattern.abstract_factory_pattern.AbstractFactory
import com.myapplication.design_pattern.abstract_factory_pattern.color.Color

class ShapeFactory :AbstractFactory(){



    override fun getShape(type: String): Shape? {
        when(type){
            "CIRCLE" -> return Circle()
            "RECTANGLE" -> return Rectangle()
            "SQUARE"  -> return Square()
        }
        return null
    }

    override fun getColor(type: String): Color? {
        return null
    }
}