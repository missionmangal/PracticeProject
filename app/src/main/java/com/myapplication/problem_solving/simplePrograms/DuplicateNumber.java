package com.myapplication.problem_solving.simplePrograms;

import com.myapplication.problem_solving.Print;
import com.myapplication.sorting.QuickSort;

public class DuplicateNumber {

    public static void main(String[] args) {
        DuplicateNumber duplicateNumber = new DuplicateNumber();
        int[] array = {5,7,6,2,4,5,2,7,5,1,2,9,4};
        duplicateNumber.removeDuplicateNumbers(array);
    }

    private void findDuplicateNumber(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if( array[Math.abs(array[i])] < 0 ){
                System.out.println("dulicate found on index = " + i +"value = " + Math.abs(array[i]));
            }else {
                array[Math.abs(array[i])] = -array[Math.abs(array[i])];
            }
        }
    }

    private void removeDuplicateNumbers(int[] arr){
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr, 0, arr.length-1);
        Print.printArray(arr);
        int previous = arr[0];
        for(int i= 1; i < arr.length;i++){
            if(arr[i] == previous){
                arr[i] = 0;
            }else {
                previous = arr[i];
            }
        }
        Print.printArray(arr);
    }
}
