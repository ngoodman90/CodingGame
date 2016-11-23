import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators
        int[] elevators = new int[nbFloors];
        
        for (int i = 0; i < nbElevators; i++) 
            elevators[in.nextInt()] = in.nextInt();

        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            int currentElevatorPos = 0;
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
            
            if (cloneFloor == exitFloor)
                currentElevatorPos = exitPos;
            else if (cloneFloor >= 0)
                currentElevatorPos = elevators[cloneFloor];
            
            if ((clonePos < currentElevatorPos && direction.equals("LEFT") 
            || clonePos > currentElevatorPos && direction.equals("RIGHT"))
            && cloneFloor >= 0)
                System.out.println("BLOCK"); // action: WAIT or BLOCK
            else
                System.out.println("WAIT"); // action: WAIT or BLOCK
        }
    }
}