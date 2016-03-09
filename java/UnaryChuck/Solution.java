package com.example.unaryChuck;

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
        String MESSAGE = in.nextLine();
        StringBuilder stringInBinary = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (char c: MESSAGE.toCharArray())
        {
            String initialParseString = Integer.toBinaryString((int)(c));
            while (initialParseString.length() < 7)
                initialParseString = "0" + initialParseString;
            stringInBinary.append(initialParseString);
        }
        char currentBit = stringInBinary.toString().toCharArray()[0];

        if (currentBit == '0')
            result.append("00 ");
        else
            result.append("0 ");

        for (char c: stringInBinary.toString().toCharArray()) {
            System.out.println(c);
            if ((int) (c) == (int) (currentBit))
                result.append("0");
            else {
                currentBit = c;
                if (c == '0')
                    result.append(" 00 0");
                else if (c == '1')
                    result.append(" 0 0");
                else
                    System.out.println("Error");
            }
        }
        System.out.println(result.toString());
    }
}
