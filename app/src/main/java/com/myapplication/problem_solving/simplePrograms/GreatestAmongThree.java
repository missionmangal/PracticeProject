package com.myapplication.problem_solving.simplePrograms;

//4. Program to find greatest of three numbers
public class GreatestAmongThree {
    public static void main(String[] args) {
        GreatestAmongThree greatestAmongThree = new GreatestAmongThree();
        System.out.println("Max =" + greatestAmongThree.findGreatestAmongThree(10, 20, 30));
        System.out.println("Max =" + greatestAmongThree.findGreatestAmongThree(20, 40, 10));
        System.out.println("Max =" + greatestAmongThree.findGreatestAmongThree(50, 10, 20));
    }

    public int findGreatestAmongThree(int x, int y, int z) {
        if (x > y && x > z) {
            return x;
        } else if (y > x && y > z) {
            return y;
        } else
            return z;
    }
}
