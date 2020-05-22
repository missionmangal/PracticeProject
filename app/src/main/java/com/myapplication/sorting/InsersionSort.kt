package com.myapplication.sorting

fun main() {
   var  arr = arrayOf(18,15,25,58,45,78,45,21,32)
    var sorting = InsersionSort()
    sorting.printArray(arr)
    sorting.insertingsorting(arr)
    sorting.printArray(arr)
}


 class InsersionSort {


    fun insertingsorting(a:Array<Int>){
        var arr =a;
        var n= arr.size

        for(i in 1..n-1){
            var key = arr[i]
            var j=i-1
            for(j in i-1 downTo 0){
                if(key < arr[j]){
                    arr[j+1] = arr[j]
                }else{
//                    arr[j]=key
                    break;
                }
            }
            arr[j+1]=key
        }
    }

    fun printArray(arr:Array<Int>){
        for(i in 0..arr.size-1){
            print("${arr[i]} ")
        }
        println()
    }

}