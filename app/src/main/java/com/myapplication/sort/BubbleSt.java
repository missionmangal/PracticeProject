package com.myapplication.sort;

public class BubbleSt {

    public static void main(String[] args) {

        int []arr = {15,48,12,47,51,26,14,21,2};
        printArray(arr);
        BubbleSt bubbleSt = new BubbleSt();
        bubbleSt.srt(arr);
        printArray(arr);
    }


    private void srt(int arr[]){
        int n = arr.length;
        for(int i = 0; i<n-1;i++){
            for(int j =0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

    }


    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
