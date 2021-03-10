package com.myapplication.problem_solving;

//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
/*Write an efficient program to find the sum of contiguous subarray within
 a one-dimensional array of numbers which has the largest sum.*/
public class LargestSumContiguousSubarray {

    public static void main(String[] args) {
        int [] arr = {-5,4,6,-2,-1,-7,3,8,1};
        Print.printArray(arr);
        findLargestSumContiguousSubArray(arr);
    }

    private static void findLargestSumContiguousSubArray(int[] arr) {
        int max_so_far =0;
        int max_ending_here =0;
        int start =0;
        int end =0;
        int s =0;
        for (int i=0;i< arr.length  ;i++) {

            max_ending_here = max_ending_here + arr[i];
            if (max_ending_here > max_so_far) {
                max_so_far = max_ending_here;
                start = s;
                end = i ;
            }
            if (max_ending_here <= 0) {
                max_ending_here = 0;
                s= i+1;
            }
        }
        System.out.println( "Longest Sum is" +max_so_far);
        System.out.println( "start index" + start);
        System.out.println( "end index" + end);

    }
}
