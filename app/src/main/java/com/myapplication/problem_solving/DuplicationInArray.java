package com.myapplication.problem_solving;


public class DuplicationInArray {

    public static void main(String[] args) {
        int[] arr = {1,5,4,5,7,6,5,4,2,8,1,2,6};
        findDuplicate(arr);
    }

    public static void findDuplicate(int []arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[Math.abs(arr[i])] >= 0) {
                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
            } else {
                 System.out.print(Math.abs(arr[i]) + ", ");
            }
        }
    }
}
