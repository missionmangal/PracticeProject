package com.myapplication.problem_solving.simplePrograms;

public class Factorial {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        int n = 5;
        int fact = factorial.findFactorial(n);
        System.out.println("Factorial of " + n + "is " + fact);
    }

    public int findFactorial(int n) {
        if (n > 1) {
            return n * findFactorial(n - 1);
        } else
            return n;
    }
}
