package com.myapplication.sort;

public class HeapSort {


    private void sort(int[]arr){
        int n= arr.length;
        //build heap
        for (int i= n/2-1;i>=0;i--)
            heapify(arr,n,i);
        //one by one extract an element from heap;
        for (int i= n-1;i>0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i]= temp;
            heapify(arr,i,0);
        }
    }

    private void heapify(int []arr ,int n,int i){
        int largest = i;
        int l = 2*i+1;
        int r = 2*i+2;
        if(l<n && arr[l]>arr[largest]){
            largest = l;
        }
        if(r<n&& arr[r]>arr[largest]){
            largest =r;
        }
        if(largest!=i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr,n,largest);
        }
    }
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    // Driver program
    public static void main(String args[])
    {
        int arr[] = {12, 11, 13, 5, 6, 7,25,75,40};
        int n = arr.length;

        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        printArray(arr);
    }
}
