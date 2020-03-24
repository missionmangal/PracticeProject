package com.myapplication.design_pattern.factory_pattern

class ShapeFactory {

    companion object{
        fun getInsance(type:String):Shape?{
            when(type){
                "CIRCLE" -> return Circle()
                "RECTANGLE" -> return Rectangle()
                "SQUARE"  -> return Square()
            }
            return null
        }
    }
}