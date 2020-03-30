package com.myapplication.design_pattern.observer_pattern

fun main() {
    var subject = Subject()
    var hexaObserver :Observer= HexaObserver(subject)

    OctalObserver(subject)
//    BinaryObserver(subject)

    println("First state change : 15")
    subject.setState(15)

    println("Second state change :10")
    subject.setState(10)

    subject.detattch(hexaObserver)
    println("Third state change : 13")
    subject.setState(13)

}
class ObserverPatternDemo {

}