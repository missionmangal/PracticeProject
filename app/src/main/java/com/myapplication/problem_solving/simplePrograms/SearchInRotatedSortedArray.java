package com.myapplication.problem_solving.simplePrograms;

import com.myapplication.problem_solving.Print;
import com.myapplication.sorting.QuickSort;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {

        SearchInRotatedSortedArray searchNumber = new SearchInRotatedSortedArray();
        int[] array = {5, 7, 6, 2, 4, 5, 2, 7, 5, 1, 2, 9, 4};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 0, array.length - 1);
        Print.printArray(array);
        searchNumber.searchValue(7, 5, array);
    }

    private void searchValue(int searchValue, int pivot, int[] arr) {

        for (int i = pivot; i != pivot - 1; ) {
            if (i == arr.length) {
                i = 0;
            }
            if (arr[i] == searchValue) {
                System.out.println("Value found at index " + i);
                break;
            }
            i++;
        }
    }
}
