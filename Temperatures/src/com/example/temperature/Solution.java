package com.example.temperature;

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
        int n = in.nextInt(); // the number of temperatures to analyse
        in.nextLine();
        String temps = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526

        int result = 5527;
        int current_int;
        Scanner sc = new Scanner(temps);

        for (int i  = 0; i < n; i++)
        {
            current_int = sc.nextInt();
            if ((Math.abs(current_int) < Math.abs(result))
                    || ((Math.abs(current_int) == Math.abs(result))
                    && (current_int > result)))
                result = current_int;


        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(result);
    }
}
