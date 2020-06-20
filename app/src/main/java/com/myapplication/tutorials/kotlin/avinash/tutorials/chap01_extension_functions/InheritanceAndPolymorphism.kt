package chap01_extension_functions

import android.util.Log

class InheritanceAndPolymorphism {
    open class Base {
        open fun getString() = "Base"
    }

    fun Base.newString() = "BaseNewString"

    class Derived : Base() {
        override fun getString() = "Derived"
    }

    fun Derived.newString() = "DerivedNewString"

    fun main(){
        var derived = Derived()
        Log.d("dsd",derived.getString())
    }

}
