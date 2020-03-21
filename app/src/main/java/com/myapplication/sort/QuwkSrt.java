package com.myapplication.sort;

public class QuwkSrt {
    public static void main(String[] args) {
//        int arr[] = {5,1,7,2,78,10,21,45,1,32,15};
        int arr[] = {5,1,7,2,78,10,21};
//        int arr[] = {10, 80, 30, 90, 40, 50, 70};
        QuwkSrt srt = new QuwkSrt();
        printArr(arr);
        srt.quickSort(arr,0,arr.length-1);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for(int i =0 ;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
    }

    private void quickSort(int arr[],int low,int high){
        if(low<high){
            int pi = partition(arr,low,high);
            printArr(arr);
            quickSort(arr,low,pi-1);
            printArr(arr);
            quickSort(arr,pi+1,high);
        }
    }
    private int partition(int arr[], int low, int high){
        int pivot = arr[high];
        int i = low-1;
        for(int j = low;j<high;j++){
            if(arr[j]<pivot){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr, i+1,high);
        System.out.println(i+1);
        return i+1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
