package com.myapplication.sort;

public class MrgSt {
//    int[] arr1 = {10, 51, 21, 45};
    public static void main(String[] args) {

//        int[] arr = {10, 51, 21, 45, 87, 12, 32, 15, 12, 152, 45};
        int[] arr = {10, 51, 21, 45};
        MrgSt obj = new MrgSt();
        obj.display(arr);
//        arr = obj.mrgSort(arr, 0, arr.length);
        System.out.println("Sorted list");
        int [] arr2=  obj.mrgSort(arr, 0, arr.length-1);
        obj.display(arr2);
    }

    private void display(int[] arr) {
        for(int i = 0;i <arr.length;i++){
            System.out.print(arr[i]+" , ");
        }
    }
    private void display1() {
//        for(int i = 0;i <arr1.length;i++){
//            System.out.print(arr1[i]+" , ");
//        }
    }

    private int [] mrgSort(int[] arr, int start, int end) {
//        if (arr.length == 1)
//            return arr;
        int arr2[] =arr;
        if (start < end) {
            int  mid =  (end+ start)/2;

            mrgSort(arr, start, mid);
            mrgSort(arr,mid+1,end);
           arr2= merge(arr,start,mid,end);
        }
        return arr2;
    }

    private int[] merge(int[] arr, int start,int mid, int end ) {
        int i,j,k=0;
        int n1 = mid - start+1;
        int n2 = end- mid;
        int arr1[] = new int[n1+n2];


        int [] L = new int [n1];
        int [] R = new int [n2];

        /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++)
            L[i] = arr[start + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[mid + 1+ j];
        i = 0;j= 0;k=0;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr1[k] = L[i++];
            }
            else
            {
                arr1[k] = R[j++];

            }
            k++;
        }

        while (i < n1)
        {
            arr1[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
//            try {
                arr1[k] = R[j];
                j++;
                k++;
//            }catch (Exception e){
//                e.fillInStackTrace();
//            }
        }

        return arr1;
    }

}
