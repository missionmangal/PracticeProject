package com.myapplication.problem_solving;

import java.util.Arrays;
import java.util.HashSet;

public class ConsecutiveSubSequence {

    public static void main(String[] args) {
        int [] arr = {1,5,3,4,6,2,7,22,5,76,3};
        findConsecutiveSequence(arr);
    }

    private static void findConsecutiveSequence(int[] arr) {
        Arrays.sort(arr);
        int count =0;
        int result =0;
        Print.printArray(arr);
        for(int i= 1; i<arr.length; i++){
            if(arr[i] == arr[i-1]+1){
                count++;
            }else
                count =1;
            if(count > result){
                result = count;
            }
        }
        System.out.println(" longest consecutive sequence is "+ result);
    }

    static int findLongestConseqSubseq(int[] arr, int n)
    {
        HashSet<Integer> S = new HashSet<Integer>();
        int ans = 0;

        // Hash all the array elements
        for (int i = 0; i < n; ++i)
            S.add(arr[i]);

        // check each possible sequence from the start
        // then update optimal length
        for (int i = 0; i < n; ++i) {
            // if current element is the starting
            // element of a sequence
            if (!S.contains(arr[i] - 1)) {
                // Then check for next elements
                // in the sequence
                int j = arr[i];
                while (S.contains(j))
                    j++;

                // update  optimal length if this
                // length is more
                if (ans < j - arr[i])
                    ans = j - arr[i];
            }
        }
        return ans;
    }

}
