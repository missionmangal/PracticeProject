package com.myapplication.problem_solving

import kotlin.math.abs


fun main() {
    val arr = arrayOf(1, 5, 4, 4, 5, 3, 2, 1, 5, 6, 7, 8, 3)
    Print.printArray(arr)
    DuplicateNumber.printDuplicateNumber(arr)
}

object DuplicateNumber {

    fun printDuplicateNumber(arr: Array<Int>) {
        println("Duplicate numbers are")
        for (i in arr.indices) {
            if (arr[abs(arr[i])] < 0) {
                print(" ${abs(arr[i])}  ,")
                arr[abs(arr[i])] = -arr[abs(arr[i])]
            } else {
                arr[abs(arr[i])] = -arr[abs(arr[i])]
            }
        }
    }

}
