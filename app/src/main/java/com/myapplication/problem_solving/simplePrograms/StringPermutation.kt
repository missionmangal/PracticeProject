package com.myapplication.problem_solving.simplePrograms

fun main() {

    val perm = StringPermutation()
    perm.permutation("","12")
}

class StringPermutation {

    fun permutation(perm: String, word: String){
        if(word.isEmpty()){
            println(perm + word)
        } else {
            for( i in word.indices) {
                permutation(perm + word[i], word.substring(0,i)
                + word.subSequence(i+1, word.length))
            }
        }

    }
}