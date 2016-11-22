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
        System.out.println(getSolution(new Scanner(System.in)));
    }
    public static String getSolution(Scanner sc)
    {
        double min_distance = Integer.MAX_VALUE;
        double defib_distance;
        double x, y;
        double defib_lon, defib_lat;
        String closest_defib = "";
        String defib_address = "";
        double position_lon = Double.parseDouble(sc.next().replace(',', '.'));
        double position_lat = Double.parseDouble(sc.next().replace(',', '.'));
        int N = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < N; i++)
        {
            String defibrillator = sc.nextLine();
            Scanner defibrillator_sc = new Scanner(defibrillator);
            defibrillator_sc.useDelimiter(";");
            for (int j = 0; j < 3; j++)
            {
                defibrillator_sc.next();
                if ( j == 0)
                    defib_address = defibrillator_sc.next();
            }

            defib_lon = Double.parseDouble(defibrillator_sc.next().replace(',', '.'));
            defib_lat = Double.parseDouble(defibrillator_sc.next().replace(',', '.'));
            x = ((defib_lon - position_lon) * (Math.cos((position_lat + defib_lat) / 2)));
            y = (defib_lat - position_lat);
            defib_distance = (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * 6371);
            if (defib_distance < min_distance)
            {
                min_distance = defib_distance;
                closest_defib = defib_address;
            }
        }
        return closest_defib;
    }
}