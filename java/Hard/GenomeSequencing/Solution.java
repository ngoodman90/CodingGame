import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static int min = Integer.MAX_VALUE;

    public static void main(String args[]) {
        int N = 3;
        int[] indexes = new int[N];
        String[] strings = {"A", "C", "AC"};
        /*for (int i = 0; i < N; i++)
        {
            String s = in.next();
            System.err.println(s);
            strings[i] = s;
        }   */
        System.err.println("Starting Permutations");
        permutations(strings, indexes, 0);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(min);
    }

    public static void permutations(String[] strings, int[] indexes, int currIndex)
    {
        int N = indexes.length;
        if (currIndex < N - 1)
        {
            for (int i = 0; i < N; i++) 
            {
                if (indexes[i] == 0)
                {
                    int[] indexClone = indexes.clone();
                    indexClone[i] = currIndex + 1;
                    permutations(strings, indexClone, currIndex + 1);
                }
            }
        }
        else
        {
            int i = 0;
            while (indexes[i] != 0 && i < N) 
                i++;
            indexes[i] = currIndex + 1;
            int ans = combinedStringLength(indexes, strings);
            if (ans < min)
                min = ans;

        }
    }

    public static int combinedStringLength(int[] indexes, String[] strings)
    {
        System.err.println(Arrays.toString(indexes));
        String combinedStrings = strings[indexes[0] - 1];
        for (int i = 0; i < indexes.length - 1; i++)
            combinedStrings += combineStrings(strings[indexes[i] - 1], strings[indexes[i + 1] - 1]);
        System.err.println(combinedStrings);
        return combinedStrings.length();
    }

    public static String combineStrings(String s1, String s2)
    {
        String substr1, substr2;
        if (s1.contains(s2))
            return "";

        for (int i = Math.min(s2.length() - 1, s1.length() - 1); i > 0; i--)
        {
            substr1 = s1.substring(s1.length() - i);
            substr2 = s2.substring(0, i);
            
            if (substr1.equals(substr2))
                return s2.substring(i);
        }
        return s2;
    }
}