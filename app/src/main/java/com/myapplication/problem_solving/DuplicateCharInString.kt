package com.myapplication.problem_solving

class DuplicateCharInString {
    companion object{
        const val NO_OF_CHAR = 256
    }
    fun fillCount(string: String , count : Array<Int>){
        for( i in string.indices){
            count[string[i].toInt()]++
        }
    }
}

fun main() {
    val string = "my name is vishnu soni"
    val count = Array(DuplicateCharInString.NO_OF_CHAR) {0}
    val duplicateCharInString = DuplicateCharInString()
    duplicateCharInString.fillCount(string, count)

    for (index in 0 until DuplicateCharInString.NO_OF_CHAR){
        if(count[index] >1){
            println("${index.toChar()} = ${count[index]}")
        }
    }
}