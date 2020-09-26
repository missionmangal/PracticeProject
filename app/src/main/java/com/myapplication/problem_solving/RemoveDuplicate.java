package com.myapplication.problem_solving;

import java.util.Arrays;

public class RemoveDuplicate {

    public static void main(String[] args) {
        int []arr  = {1,2,5,4,3,14,8,4,8,3,6,11,25,12};
        System.out.println("Array before Removing Duplicate");
        printArrary(arr);
        int []result = removeDuplicate(arr);
        System.out.println("Array After Removing Duplicate");
        printArrary(result);
    }

    private static void printArrary(int[] arr) {
        for (int value : arr) {
            System.out.print(value + ",");
        }
        System.out.println();
    }

    private static int[] removeDuplicate(int[] arr)  {
        Arrays.sort(arr);
        int [] result = new int[arr.length];
        int previous = arr[0];
        result[0] = arr[0];
        for(int i = 1; i< arr.length;i++){
            if(previous == arr[i]){
                result[i] =0;
            }else {
                result[i] = arr[i];
            }
            previous = arr[i];
        }

        return result;
    }
}
