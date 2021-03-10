package com.myapplication.kotlin

fun main() {
    val employee1 = EmployeeData("Vishnu",27)
    val employee2 = EmployeeData("Ravi",32)
    val employee3 = EmployeeData("Roshan",52, "Electrical")
    val employee4 = EmployeeData("Vishnu",27)

    var isSame = "not same"
    if(employee1 === employee4)
        isSame = "same"
    println("Employee 1 and Employee 4 are $isSame")

    isSame = "not same"
    if(employee1 === employee2)
        isSame = "same"
    println("Employee 1 and Employee 4 are $isSame")

    val set = HashSet<EmployeeData>()
    set.add(employee1)
    set.add(employee2)
    set.add(employee3)
    set.add(employee4)
    println("Set size is $set")
}
