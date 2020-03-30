package com.myapplication.design_pattern.abstract_factory_pattern.color

import com.myapplication.design_pattern.abstract_factory_pattern.AbstractFactory
import com.myapplication.design_pattern.abstract_factory_pattern.shape.Shape

class ColorFactory : AbstractFactory(){

    override fun getShape(type:String): Shape? {
        return null
    }

    override fun getColor(type:String):Color? {
        when(type){
            "RED" -> return Red()
            "GREEN" -> return Green()
            "BLUE"  -> return Blue()
        }
        return null
    }
}