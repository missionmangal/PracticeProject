package com.myapplication.problem_solving;

import org.jetbrains.annotations.NotNull;

public class Print {

    public static void printArray(int[] arr){
        System.out.println();
        for(int value : arr){
            System.out.print(value +" , ");
        }

        System.out.println();
    }

    public static void printArray(@NotNull Integer[] arr) {
        System.out.println();
        for(int value : arr){
            System.out.print(value +" , ");
        }

        System.out.println();
    }
}
