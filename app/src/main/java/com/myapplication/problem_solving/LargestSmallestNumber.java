package com.myapplication.problem_solving;

public class LargestSmallestNumber {

    public static void main(String[] args) {
        int[] arr = {89,5,4,23,1,5,9,10,12,45,-5};
        findLargestAndSmallestNumber(arr);
    }
    public static void findLargestAndSmallestNumber(int [] arr ){
        int smallest = arr[0];
        int largest = arr[0];
        for(int i =1; i< arr.length ; i++){
            if(smallest > arr[i]){
                smallest = arr[i];
            }
            if(largest < arr[i]){
                largest = arr[i];
            }
        }
        System.out.println("Smallest = "+smallest +" Largest = " + largest);
    }
}
