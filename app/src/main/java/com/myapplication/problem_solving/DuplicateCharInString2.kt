package com.myapplication.problem_solving

fun main() {
    val string = "my name is vishnu"
    println(string)
    DuplicateCharInString2.printDuplicateChar(string)
}

object DuplicateCharInString2 {
    fun printDuplicateChar(string: String) {
        val stringArr = string.toCharArray()
        val charArr = Array<Int>(256) { 0 }

        for (char in stringArr) {
            charArr[char.toInt()] = charArr[char.toInt()] + 1
        }
        for (char in stringArr) {
            if (charArr[char.toInt()] > 0) {
                print("$char = ${charArr[char.toInt()]}  ")
                charArr[char.toInt()] = 0
            }
        }
    }
}