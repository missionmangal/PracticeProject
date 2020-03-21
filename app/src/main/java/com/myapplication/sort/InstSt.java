package com.myapplication.sort;

public class InstSt {

    public static void main(String[] args) {
        int arr[] = {21,60,32,01,41,34,5};
        InstSt instSt = new InstSt();
        print(arr);
        instSt.st(arr);
        print(arr);
    }

    private static void print(int[] arr) {

        System.out.println();
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + ", ");
        }
    }

    public void st(int[] arr){
        for(int i = 1;i<arr.length;i++){
            int val = arr[i];
            int pos = i-1;

            for(;pos>=0&&arr[pos]>val;){
                arr[pos+1] = arr[pos];
                pos--;

            }

            arr[pos+1] = val;
            print(arr);
        }
    }
}
