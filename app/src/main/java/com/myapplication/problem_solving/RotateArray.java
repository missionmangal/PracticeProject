package com.myapplication.problem_solving;

/*How to Rotate an Array to Left/Right by a Given Number in Java - Coding Problem

Read more: https://www.java67.com/2018/05/how-to-rotate-array-left-and-right-by-given-number.html#ixzz6Zs6mAVK8*/
public class RotateArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Array before rotation");
        Print.printArray(arr);
        int leftRotateTime = 3;
        int rightRotateTime = 5;
//        Rotate Right
        rotateRight(arr, rightRotateTime);

        System.out.println("Array After Right rotation");
        Print.printArray(arr);
//        Rotate Left
        rotateLeft(arr, leftRotateTime);
        System.out.println("Array After Left rotation");
        Print.printArray(arr);


    }

    private static void rotateRight(int[] arr, int rightRotateTime) {
        for (int j = 0; j < rightRotateTime; j++) {
            int temp = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (i < arr.length - 1) {
                    int tp = arr[i + 1];
                    arr[i + 1] = temp;
                    temp = tp;
                } else {
                    arr[0] = temp;
                }
            }
        }
    }

    private static void rotateLeft(int[] arr, int leftRotateTime) {
        for (int j = 0; j < leftRotateTime; j++) {
            int temp = arr[arr.length - 1];
            for (int i = arr.length - 1; i >= 0; i--) {
                if (i > 0) {
                    int tp = arr[i - 1];
                    arr[i - 1] = temp;
                    temp = tp;
                } else {
                    arr[arr.length - 1] = temp;
                }
            }
        }
    }
}
