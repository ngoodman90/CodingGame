package com.ngoodman90;
/**
 * Created by ngoodman90 on 12/9/2015.
 */
import java.util.*;

import java.io.*;
import java.math.*;

class Solution
{
    private static class Node {

        public static int numOfNodes;

        private String id;
        private LinkedList<Node> children;


        public Node()
        {
            this.id = "";
            this.children = new LinkedList<>();
        }

        public Node(String number)
        {
            Node.numOfNodes++;
            this.id = number.substring(0,1);
            this.children = new LinkedList<>();
            if (number.length() > 1)
            {
                this.children.add(new Node(number.substring(1)));
            }
        }

        public void addChildToNode(String newNum)
        {
            if (!(this.containsId(newNum.substring(0,1))))
            {
                this.children.add(new Node(newNum));
            }

        }

        public String getId() {
            return id;
        }

        public LinkedList<Node> getChildren() {
            return children;
        }

        public boolean containsId(String newId)
        {
            for (Node childNode : this.children)
            {
                if (childNode.getId().equals(newId))
                {
                    return true;
                }
            }
            return false;
        }

        public String notContainedNumber(String telephone)
        {
            while (this.containsId(telephone.substring(1, 0)))
            {

            }
            return telephone;
        }
    }
    public static void main(String args[])
    {
        System.out.println(new Scanner(System.in)); // The number of elements (referencing a number) stored in the structure.
    }

     public static int getSolution(Scanner in)
     {
         Node root = new Node();
         int N = in.nextInt();
         for (int i = 0; i < N; i++) {
             String telephone = in.next();
             String newNumberToAdd = root.notContainedNumber(telephone);
             if (!(root.containsNumber(telephone))) {
                 root.addChildToNode(telephone);
             }
         }
         return Node.numOfNodes;
     }
}

