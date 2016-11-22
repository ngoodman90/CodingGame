package com.ngoodman90;

/**
 * Created by ngoodman90 on 2/23/2016.
 */
import java.awt.*;
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import static java.lang.System.exit;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    private final static Logger LOGGER = Logger.getLogger(Solution.class.getName());

    public static void main(String args[]) {
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Starting Parsing");
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        int[][] passed_these_squares = new int[L][C];
        boolean breaker_mode = false;
        boolean is_inverted = false;
        int num_of_moves = 0;
        Point teleport_1 = new Point(0, 0);
        Point teleport_2 = new Point(0, 0);;
        String[] bender_map = new String[L];
        Point bender_location = new Point(0, 0);
        StringBuilder answer = new StringBuilder();
        in.nextLine();
        for (int i = 0; i < L; i++) {
            bender_map[i] = in.nextLine();
            if (bender_map[i].contains("@"))
            {
                bender_location.setLocation(i, bender_map[i].indexOf('@'));
                LOGGER.info("Starting Location " + bender_location.toString());
            }
            if (bender_map[i].contains("T"))
            {
                if ((int)teleport_1.getX() == 0)
                    teleport_1.setLocation(i, bender_map[i].indexOf('T'));
                else
                    teleport_2.setLocation(i, bender_map[i].indexOf('T'));
            }
        }
        /*finished parsing*/

        /*mark the starting square as traversed*/
        passed_these_squares[(int)bender_location.getX()][(int)bender_location.getY()] = 1;

        /*get char at bender position and make sure its a '@'*/
        char bender_location_char = bender_map[(int)bender_location.getX()].charAt((int)bender_location.getY());
        if (bender_location_char != '@')
        {
            LOGGER.info("Incorrect starting location");
            exit(-1);
        }

        Direction curr_direction = Direction.SOUTH;
        Point try_this_location;
        while (bender_location_char != '$')
        {
            num_of_moves++;
            if (num_of_moves > C*L)
            {
                System.out.println("LOOP");
                exit(0);
            }
            try_this_location = advanceByDirection(curr_direction, bender_location);
            if (isLegalMove(bender_map, try_this_location, breaker_mode))
            {
                /*update new location, update char, and update answer*/
                bender_location = try_this_location;
                bender_location_char = bender_map[(int)bender_location.getX()].charAt((int)bender_location.getY());
                answer.append(curr_direction + "\n");
                LOGGER.info("Moved " + curr_direction + " to location " + bender_location.toString());
                switch (bender_location_char)
                {
                    case 'S':
                        curr_direction = Direction.SOUTH;
                        break;
                    case 'E':
                        curr_direction = Direction.EAST;
                        break;
                    case 'N':
                        curr_direction = Direction.NORTH;
                        break;
                    case 'W':
                        curr_direction = Direction.WEST;
                        break;
                    case 'B':
                        breaker_mode = !breaker_mode;
                        LOGGER.info("Breaker mode  = " + breaker_mode);
                        break;
                    case 'X':
                        if (breaker_mode == false)
                        {
                            LOGGER.info("Illegal move, not in breaker mode!");
                            exit(-1);
                        }
                        else
                        {
                            /*need to delete the 'X' from current position*/
                            String new_row = bender_map[(int)bender_location.getX()];
                            bender_map[(int)bender_location.getX()] = new_row.substring(0, (int)bender_location.getY()) + ' ' + new_row.substring((int)bender_location.getY() + 1);
                            LOGGER.info("Removing X from " + bender_location.toString());
                        }
                        break;
                    case 'I':
                        is_inverted = !is_inverted;
                        LOGGER.info("is_inverted  = " + is_inverted);
                        break;
                    case 'T':
                        if (bender_location.equals(teleport_1))
                            bender_location = teleport_2;
                        else
                            bender_location = teleport_1;
                        break;
                }
            }
            else
            {
            /*have to try different direction*/
                if (!is_inverted)
                {
                    try_this_location = advanceByDirection(Direction.SOUTH, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.SOUTH;
                        LOGGER.info("Changing direction to SOUTH");
                        continue;
                    }
                    try_this_location = advanceByDirection(Direction.EAST, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.EAST;
                        LOGGER.info("Changing direction to EAST");
                        continue;
                    }
                    try_this_location = advanceByDirection(Direction.NORTH, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.NORTH;
                        LOGGER.info("Changing direction to NORTH");
                        continue;
                    }
                    try_this_location = advanceByDirection(Direction.WEST, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.WEST;
                        LOGGER.info("Changing direction to WEST");
                        continue;
                    }
                }
                else
                {
                    try_this_location = advanceByDirection(Direction.WEST, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.WEST;
                        LOGGER.info("Changing direction to WEST");
                        continue;
                    }
                    try_this_location = advanceByDirection(Direction.NORTH, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.NORTH;
                        LOGGER.info("Changing direction to NORTH");
                        continue;
                    }
                    try_this_location = advanceByDirection(Direction.EAST, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.EAST;
                        LOGGER.info("Changing direction to EAST");
                        continue;
                    }
                    try_this_location = advanceByDirection(Direction.SOUTH, bender_location);
                    if (isLegalMove(bender_map, try_this_location, breaker_mode))
                    {
                        curr_direction = Direction.SOUTH;
                        LOGGER.info("Changing direction to SOUTH");
                        continue;
                    }
                }
            }
        }
        System.out.println(answer.toString());
    }

    public enum Direction
    {
        SOUTH, NORTH, EAST, WEST
    }

    public static Point advanceByDirection(Direction direction, Point curr_location)
    {
        Point new_location = new Point(0, 0);
        switch (direction) {
            case SOUTH:
                new_location.setLocation((int)curr_location.getX() + 1, (int)curr_location.getY());
                break;
            case NORTH:
                new_location.setLocation((int)curr_location.getX() - 1, (int)curr_location.getY());
                break;
            case EAST:
                new_location.setLocation((int)curr_location.getX(), (int)curr_location.getY() + 1);
                break;
            case WEST:
                new_location.setLocation((int)curr_location.getX(), (int)curr_location.getY() - 1);
                break;
        }
        return new_location;
    }

    public static boolean isLegalMove(String[] bender_map, Point location, boolean breaker_mode)
    {
        char location_char = bender_map[(int)location.getX()].charAt((int)location.getY());
        return !(((location_char == 'X')&&(breaker_mode == false)) || (location_char == '#'));

    }
}