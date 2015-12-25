package com.example.War;

import java.util.*;

class Solution {

    public static void main(String args[])
    {

        LinkedList <String> pl1 = new LinkedList<String>();
        LinkedList <String> pl2 = new LinkedList <String>();
        LinkedList <String> warList1 = new LinkedList <String>();
        LinkedList <String> warList2 = new LinkedList <String>();

        Scanner in = new Scanner(System.in);
        int turns;

        scanCards(in.nextInt(), in, pl1);
        scanCards(in.nextInt(), in, pl2);

        for (turns = 0; (isWon(pl1.size(), pl2.size()) == -1); turns++)
        {
            playRound(pl1, pl2, warList1, warList2);
        }

        printResults(pl1, pl2, turns);
    }

    public static void scanCards(int numOfCards, Scanner in, LinkedList pl) {
        for (int i = 0; i < numOfCards; i++) {
            pl.add(in.next());
        }
    }

    public static int isWon(int n, int m)
    {
        if ((n <= 0) && (m <= 0))
            return 0;
        if (n <= 0) //player 1 lost
            return 2;
        if (m <= 0) //player 2 lost
            return 1;
        else //nobody lost
            return -1;
    }

    public static int convertCard(String card)
    {
        switch(card.substring(0,1))
        {
            case "A":
                return 14;
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            default:
                    return Integer.parseInt(card.substring(0, (card.length() - 1)));
        }
    }

    public static void playRound(LinkedList pl1, LinkedList pl2, LinkedList warList1, LinkedList warList2)
    {
        int card1Value = convertCard((String)pl1.getFirst());
        int card2Value = convertCard((String)pl2.getFirst());

        if (card1Value == card2Value)
        {
            System.out.println("war");
            war(pl1, pl2, warList1, warList2);
        }
        if (card1Value > card2Value)
        {
            pl1WInsRound(pl1, pl2, warList1, warList2);
        }
        if (card2Value > card1Value)
        {
            pl2WInsRound(pl1, pl2,warList1, warList2);
        }
    }

    public static void war(LinkedList pl1,LinkedList pl2, LinkedList warList1, LinkedList warList2)
    {
        if ((pl1.size() < 5) || (pl2.size() < 5))
        {
            System.out.println("PAT");
            System.exit(0);
        }
        else
        {
            for (int i = 0; i < 4; i++)
            {
                warList1.add(pl1.removeFirst());
            }
            for (int i = 0; i < 4; i++)
            {
                warList2.add(pl2.removeFirst());
            }
        }
        playRound(pl1, pl2, warList1, warList2);
    }

    public static void pl1WInsRound(LinkedList pl1, LinkedList pl2, LinkedList warList1, LinkedList warList2)
    {
        if (warList1.size() > 0)
        {
            pl1WInsWar(pl1, pl2, warList1, warList2);
        }
        else
        {
            pl1.add(pl1.removeFirst());
            pl1.add(pl2.removeFirst());
        }

    }
    public static void pl2WInsRound(LinkedList pl1, LinkedList pl2, LinkedList warList1, LinkedList warList2)
    {
        if (warList1.size() > 0)
        {
            pl2WInsWar(pl1, pl2, warList1, warList2);
        }
        else
        {
            pl2.add(pl1.removeFirst());
            pl2.add(pl2.removeFirst());
        }

    }

    public static void pl1WInsWar(LinkedList pl1, LinkedList pl2, LinkedList warList1, LinkedList warList2)
    {
        while (warList1.size() > 0)
        {
            pl1.add(warList1.removeFirst());
        }
        pl1.add(pl1.removeFirst());

        while (warList2.size() > 0)
        {
            pl1.add(warList2.removeFirst());
        }
        pl1.add(pl2.removeFirst());
    }

    public static void pl2WInsWar(LinkedList pl1, LinkedList pl2, LinkedList warList1, LinkedList warList2)
    {
        while (warList1.size() > 0)
        {
            pl2.add(warList1.removeFirst());
        }
        pl2.add(pl1.removeFirst());

        while (warList2.size() > 0)
        {
            pl2.add(warList2.removeFirst());
        }
        pl2.add(pl2.removeFirst());
    }

    public static void printResults(LinkedList pl1, LinkedList pl2, int turns)
    {
        if (isWon(pl1.size(), pl2.size()) == 0)
        {
            System.out.println("PAT");
        }
        else
        {
            System.out.println(isWon(pl1.size(), pl2.size()) + " " + turns);
        }
    }
}