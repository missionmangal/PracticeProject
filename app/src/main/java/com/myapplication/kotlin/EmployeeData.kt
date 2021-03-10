package com.myapplication.kotlin

data class EmployeeData(var name: String, var age: Int) {

    var department: String? = null

    constructor(name: String, age: Int, department: String) : this(name, age) {
        this.department = department
    }

    fun isAbleToCastVote(): Boolean {
        return age > 18
    }
}