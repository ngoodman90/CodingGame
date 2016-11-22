import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position
        
        int thorX = initialTX;
        int thorY = initialTY;
        String directionX = "";
        String directionY = "";

        // game loop
        while (true) 
        {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.
            if (thorX == lightX)
            {
                directionX = "";
            }
            if (thorX < lightX)
            {
                thorX++;
                directionX = "E";
            }
            if (thorX > lightX)
            {
                thorX--;
                directionX = "W";
            }
            if (thorY == lightY)
            {
                directionY = "";
            }
            if (thorY < lightY)
            {
                thorY++;
                directionY = "S";
            }
            if (thorY > lightY)
            {
                thorY--;
                directionY = "N";
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(directionY + directionX); // A single line providing the move to be made: N NE E SE S SW W or NW
        }
    }
}