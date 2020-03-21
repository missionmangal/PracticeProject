package com.myapplication.twoInterfaceSameMethod

fun main(args:Array<String>){
    var testingClass = TestingClass().also {
        it.sameMethod()
        var first: FirstInterface = it
        var secInterface: SecInterface = it
        first.sameMethod()
        secInterface.sameMethod()
    }

}
class TestingClass : FirstInterface,SecInterface {
    override fun test() {

    }

    override fun test(a: Int) {

    }

    override fun sameMethod() {
        print("Hello man\n")
    }




}