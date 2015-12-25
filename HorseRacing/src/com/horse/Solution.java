package com.horse;

/**
 * Created by ngoodman90 on 12/9/2015.
 */
import java.util.*;
import java.io.*;
import java.math.*;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] horseArray = new int[N];
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            horseArray[i] = pi;
        }

        Arrays.sort(horseArray);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < N - 1; i++)
        {
            int localDiff = horseArray[i + 1] - horseArray[i];
            if (localDiff < minDiff)
                minDiff = localDiff;
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(minDiff);
    }
}