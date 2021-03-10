package com.myapplication.problem_solving.simplePrograms;

public class PrimeNumber {

    public static void main(String[] args) {
        PrimeNumber primeNumber = new PrimeNumber();
        int number = 45;
        boolean isPrime = primeNumber.isPrimeNumber(number);
        if (isPrime)
            System.out.println(number + " is prime number");
        else
            System.out.println(number + " is not prime number");
        number = 47;
        isPrime = primeNumber.isPrimeNumberUsingRecursion(number, 2);
        if (isPrime)
            System.out.println(number + " is prime number");
        else
            System.out.println(number + " is not prime number");
    }

    public boolean isPrimeNumber(int number) {
        boolean isPrime = true;
        for (int i = 2; i < number - 1; i++) {
            if (number % i == 0) {
                System.out.println(i);
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public boolean isPrimeNumberUsingRecursion(int n, int i) {

        if (n <= 2) {
            return (n == 2);
        }
        if (n % i == 0) {
            return false;
        }
        if (i * i > n) {
            return true;
        }
        return isPrimeNumberUsingRecursion(n, i + 1);
    }
}
