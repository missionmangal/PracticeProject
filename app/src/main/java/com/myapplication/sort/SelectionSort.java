package com.myapplication.sort;

public class SelectionSort {

    public static void main(String[] args) {

        int arr[] = {12,54,12,34,54,34,32,12,43,65,45,75,34};

        printArray(arr);
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.selSort(arr);
        printArray(arr);
    }

    private void selSort(int [] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[i]){
                    swap(arr,i,j);
                }
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        arr[i] = arr[i]*arr[j];
        arr[j] = arr[i]/arr[j];
        arr[i] = arr[i]/arr[j];
    }
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
