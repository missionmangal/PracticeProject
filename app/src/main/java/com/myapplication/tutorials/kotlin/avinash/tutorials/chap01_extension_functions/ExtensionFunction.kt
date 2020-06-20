package chap01_extension_functions

import com.myapplication.tutorials.kotlin.avinash.extensions.respestfulName

fun main() {
    var extFun = ExtensionFunction()
    extFun.respestfulName = "Avinash"
    System.out.println(extFun.respestfulName)
//    "ffdgfd".developerName = "Vishnu"
//    System.out.println("ffdgfd".developerName)
}


class ExtensionFunction {

    var name:String = "Avinash"
    var developerName: String? = "Avinash"
       get() {
           return field
       }
        set(value) {
            field = value
        }
}