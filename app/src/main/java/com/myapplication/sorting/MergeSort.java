package com.myapplication.sorting;

public class MergeSort {

    public static void main(String[] args) {
        System.out.print("hellog");
        int arr[] ={15,21,54,01,06,32,45,75,14,25,324,15};
        print(arr);
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr,0,arr.length-1);
        print(arr);

    }

    void mergeSort(int[]arr,int min,int max){
        if(min<max){
            int mid = (min+max)/2;
            mergeSort(arr,min,mid);
            mergeSort(arr,mid+1,max);
            merge(arr,min,max,mid);
        }
    }

    private void merge(int[] arr, int min, int max, int mid) {
        int l1= mid-min+1;
        int l2 = max-mid;

        int i=0,j=0,k=0;

        int temp1[]=new int[l1];
        int temp2[]=new int[l2];

        for(;   i<l1   ;i++){

            temp1[i] = arr[min+i];

        }
        for(j=0;j<l2;j++)
            temp2[j]=arr[mid+1+j];

        i=0;j=0;k=min;
        for(;   i<l1  &&  j<l2;   k++){

            if(temp1[i]<temp2[j]){
                arr[k]=temp1[i++];
            }else
                arr[k]= temp2[j++];
        }
        if(j<l2){
            for(;j<l2;j++,k++){
                arr[k]=temp2[j];
            }
        }

        if(i<l1) {
            for (; i < l1; i++, k++) {
                arr[k] = temp1[i];
            }
        }
        print(arr);
    }

    private static void print(int[] arr) {

        System.out.println();
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + ", ");
        }
    }
}
