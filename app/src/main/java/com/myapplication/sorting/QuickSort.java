package com.myapplication.sorting;

public class QuickSort {
    public static void main(String[] args) {

        int[] arr = {12,51,21,45,74,10,21,54,32};
        QuickSort sort = new QuickSort();
        print(arr);
        sort.quickSort(arr,0,arr.length);
        print(arr);
    }

    void quickSort(int arr[],int min , int max){
        if(min<max){
            int pi = partition(arr,min,max);

            quickSort(arr,min,pi-1);
            quickSort(arr,pi+1,max);
        }
    }
    int partition(int arr[],int min,int max){
        int i=min-1;
        int j= min;
        int pivot = arr[max];
        for(;j<max;j++){
            if(arr[j]<pivot){
                i++;
                swap(arr, i , j);
            }
        }
        i++;
        swap(arr,i,max);
        return i;
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2]= temp;
    }
    private static void print(int[] arr) {

        System.out.println();
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + ", ");
        }
    }
}
