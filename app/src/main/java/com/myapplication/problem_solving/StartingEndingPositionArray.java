package com.myapplication.problem_solving;

public class StartingEndingPositionArray {
    public static void main(String[] args) {
        int [] arr = {1,2,2,3,3,4,4,4,4,4,4,7,10,12,15,18};
        int searchValue = 4;
        Print.printArray(arr);
        findPosition(arr,searchValue);
    }

    private static void findPosition(int[] arr , int searchValue) {
        int left =0, right = arr.length-1;
        int start = -1, end =-1;
        int searchIndex = -1;
        while (left <= right){
            int mid = (left +right)/2;
            System.out.println("mid index = "+ mid +" value = "+arr[mid]);
            if(searchValue == arr[mid] ){
                searchIndex = mid;
                break;
            }else if( searchValue < arr[mid]){
                right--;
            }else if(searchValue > arr[mid]){
                left++;
            }
        }
        start = searchIndex;
        end = searchIndex;
        int sLeft = searchIndex;
        int sRight = searchIndex;
        while (true){
            sLeft--;
            sRight++;
            if( (sLeft <0 || arr[sLeft] != searchValue ) && (sRight >= arr.length  || arr[sRight] != searchValue)){
                break;
            }
            if(sLeft >=0 && arr[sLeft] == searchValue ){
                start = sLeft;
            }
            if(sRight < arr.length && arr[sRight] == searchValue ){
                end  = sRight;
            }
        }
        System.out.println("start index = "+ start +" end index = " + end);
     }
}
