package com.myapplication.sort;

public class HeapSt2 {
    public static void main(String[] args) {
        int arr[] = {12,51,48,45,14,26,24,15,84,43};
        HeapSt2 obj = new HeapSt2();
        obj.printArray(arr);
        
    }

    private void sort(int arr[]){
        int n = arr.length;
        for(int i = n/2-1;i>=0;i--)
            heapify(arr,n,i);
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = i*2+1;
        int right = i+2+2;
        if(left<n && arr[left]>arr[largest]){
            largest = left;
        }

        if(right<n && arr[right]>arr[largest]){
            largest  = right;
        }

        if(i!= largest){
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr,n,largest);
        }
    }


    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
