package com.myapplication.problem_solving.simplePrograms;
//3. Reverse an input number using recursion
public class ReverseNumber {

    public static void main(String[] args) {
        ReverseNumber reverseNumber = new ReverseNumber();
        reverseNumber.reverseNumberMethod(1983);
        System.out.println();
    }

    public void reverseNumberMethod(int number){
        if(number>0){
            int remainder = number%10;
            System.out.print(remainder);
            reverseNumberMethod(number/10);
        }
    }
}
