package com.myapplication.problem_solving;

/*Search an element in a sorted and rotated array*/
/*https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/*/
//Solution for ascending array list

public class BinarySearchRotatedArray {
    public static void main(String[] args) {
        int [] arr = {40,50,60,70,80,90,100,10,20,30};
        int key = 30;
        search(arr,key);
    }

    private static void search(int[] arr, int key) {
        int pivot = 0;
        for(int i =0 ;i < arr.length-1 ; i++){
            if(arr[i] > arr[i+1]){
                pivot = i;
            }
        }
        System.out.println("Pivot index is = " +pivot);
        if(pivot !=0){
            int left1 = 0;
            int right1 = pivot;
            int left2 = pivot+1;
            int right2 = arr.length-1;

            boolean found = binarySearch(arr, left1, right1, key);
            if(found){
                return;
            }else {
                found = binarySearch(arr, left2, right2, key);
                if(!found){
                    System.out.println("Key did not find " );
                }
            }
        }
    }

    private static boolean binarySearch(int[] arr, int left, int right, int key) {
        while (left <= right){
            int mid = (left + right)/2;
            if(arr[mid] == key){
                System.out.println("Key found at index = " + mid);
                return true;
            }else if(key > arr[mid]){
                left++;
            }else if(key < arr[mid]){
                right--;
            }
        }
        return false;
    }

}
