import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) 
    {
        System.out.println(getMaxLoss(new Scanner(System.in)));
    }
    
    public static int getMaxLoss(Scanner sc)
    {
        int n = sc.nextInt();
        int max_exchange = sc.nextInt();;
        int max_loss = 0;
        for (int i = 1; i < n; i++) {
            int v = sc.nextInt();
            if ((v - max_exchange) < max_loss)
                max_loss = v - max_exchange;
            if (v > max_exchange)
                max_exchange = v;
        }
        return max_loss;
    }
}