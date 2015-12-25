package com.ngoodman90

/**
 * Created by ngoodman90 on 12/22/2015.
 */
import org.junit.Test;
import static org.junit.Assert.*;


public class solutionTestTest{

    @Test
    void testGetSolution1()
    {
        String input_string = "1\n" +
                "0467123456";
        Scanner input_scanner = new Scanner(input_string);

        int expected = 10;
        int result = Solution.getSolution(input_scanner);

        assertEquals(expected, result, 0.0);

    }

    @Test
    void testGetSolution2()
    {
        String input_string = "2\n" +
                "0123456789\n" +
                "1123456789";
        Scanner input_scanner = new Scanner(input_string);

        int expected = 20;
        int result = Solution.getSolution(input_scanner);

        assertEquals(expected, result, 0.0);

    }

    @Test
    void testGetSolution3()
    {
        String input_string = "2\n" +
                "0123456789\n" +
                "0123";
        Scanner input_scanner = new Scanner(input_string);

        int expected = 10;
        int result = Solution.getSolution(input_scanner);

        assertEquals(expected, result, 0.0);

    }

    @Test
    void testGetSolution4()
    {
        String input_string = "5\n" +
                "0412578440\n" +
                "0412199803\n" +
                "0468892011\n" +
                "112\n" +
                "15";
        Scanner input_scanner = new Scanner(input_string);

        int expected = 28;
        int result = Solution.getSolution(input_scanner);

        assertEquals(expected, result, 0.0);

    }


}
