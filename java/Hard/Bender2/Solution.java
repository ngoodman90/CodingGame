package com.ngoodman90;

/**
 * Created by ngoodman90 on 2/25/2016.
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private final static Logger LOGGER = Logger.getLogger(Solution.class.getName());

    public static void main(String args[])
    {
        LOGGER.setLevel(Level.INFO);
        int max_money = 0;
        Scanner in = new Scanner(System.in);
        Scanner room_scanner;
        int N = in.nextInt();
        int[][] room_array = new int[N][5];
        in.nextLine();
        for (int i = 0; i < N; i++)
        {
            room_scanner = new Scanner(in.nextLine());
            room_scanner.next();
            room_array[i][0] = room_scanner.nextInt();
            if (room_scanner.hasNextInt())
                room_array[i][1] = room_scanner.nextInt();
            else
            {
                room_scanner.next();
                room_array[i][1] = -1;
            }

            if (room_scanner.hasNextInt())
                room_array[i][2] = room_scanner.nextInt();
            else
            {
                room_scanner.next();
                room_array[i][2] = -1;
            }

            room_array[i][3] = 0;
            room_array[i][4] = 0;
        }
        room_array[0][4] = 1;
        for (int i = 0; i < N; i++)
        {
            if (room_array[i][4] == 1)
            {
                if (room_array[i][1] != -1)
                    room_array[room_array[i][1]][4] = 1;
                if (room_array[i][2] != -1)
                    room_array[room_array[i][2]][4] = 1;
            }
        }
        for (int i = 0; i < N; i++)
        {
            if (room_array[i][1] != -1)
            {
                if (room_array[room_array[i][1]][3] < room_array[i][3] + room_array[i][0])
                    room_array[room_array[i][1]][3] = room_array[i][3] + room_array[i][0];
            }
            if (room_array[i][2] != -1)
            {
                if (room_array[room_array[i][2]][3] < room_array[i][3] + room_array[i][0])
                    room_array[room_array[i][2]][3] = room_array[i][3] + room_array[i][0];
            }
            if ((room_array[i][3] + room_array[i][0] > max_money) && (room_array[i][4] == 1))
            {
                max_money = room_array[i][3] + room_array[i][0];
                LOGGER.info("Max money path ends in room " + i + " with " + max_money);
            }
        }
        System.out.println(max_money);
    }
}
