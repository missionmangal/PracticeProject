package com.myapplication.sort;

public class SortClass {
    public static void main(String[] args) {
        
        int arr[] ={10,20,30,40,15,30,70,40,25,35};
        int n = 10;
        selectionSort(arr);
    }

    private static void selectionSort(int[] arr) {
        int min =0;
        for(int i =1;i<arr.length;i++){
            int j=i+1;
            min = i;
            for(;j<arr.length;j++){
                if(arr[min]>arr[j]){
                    min = j;
                }
            }

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i]=temp;

        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

}
