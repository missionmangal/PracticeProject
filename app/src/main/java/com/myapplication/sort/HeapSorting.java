package com.myapplication.sort;

public class HeapSorting {

    public static void main(String[] args) {
        int arr[] = {5,4,10,13,20,25};
        printArray(arr);
        HeapSorting sort = new HeapSorting();
        sort.heapSort(arr);
        printArray(arr);
    }

    void heapSort(int[] arr){
        int n = arr.length;
        for(int i = n/2-1;i>=0;i--){
            heapify(arr,n,i);
        }

        for(int i= n-1;i>0;i--){
            swap(arr,0,i);
            heapify(arr,i,0);
        }

    }
    void heapify(int[]arr,int n,int i){
        int large = i;
        int left = large*2+1;
        int right = large*2+2;

        if(left < n && arr[left] > arr[large] ){
            large = left;
        }
        if(right < n && arr[right] > arr[large]){
            large = right;
        }

        if(i!=large){
            swap(arr,i,large);
            heapify(arr,n,large);
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
