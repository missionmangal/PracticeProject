package com.myapplication.problem_solving.simplePrograms;

import com.myapplication.problem_solving.Print;
import com.myapplication.sorting.QuickSort;

public class PairsOfInteger {

    public static void main(String[] args) {
        int[] array = {1, 4, 7, 5, 2, 6, 4, 8, 9, 6, 3, 2, 1, 5, 6, 3, 12, 5};
        int sum = 12;
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 0, array.length - 1);
        Print.printArray(array);
        findPairs(array, sum);
    }

    private static void findPairs(int[] array, int sum) {
        for (int i = 0, j = array.length - 1; i <= array.length / 2;) {
            if (array[i] + array[j] == sum) {
                System.out.println("pair (" + array[i] + "," + array[j] + ")");
                i++;
                j--;
            } else if (array[i] + array[j] > sum) {
                j--;
            } else {
                i++;
            }
        }
    }
}
