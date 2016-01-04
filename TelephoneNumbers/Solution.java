package com.ngoodman90;
/**
 * Created by ngoodman90 on 12/9/2015.
 */
import java.util.*;

import java.io.*;
import java.math.*;

import static java.lang.System.exit;

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
            if (this.children != null)
            {
                for (Node childNode : this.children)
                {
                    if (childNode.getId().equals(newId))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        public Node nodeContainsId(String newId)
        {
            for (Node childNode : this.children)
            {
                if (childNode.getId().equals(newId))
                {
                    return childNode;
                }
            }
            System.out.println("couldnt find child node, even though it was supposed to be found");
            exit(0);
            return new Node();
        }

        public void notContainedNumber(String telephone)
        {
            if (telephone.length() > 0)
            {
                if (this.containsId(telephone.substring(0, 1)))
                {
                    this.nodeContainsId(telephone.substring(0, 1)).notContainedNumber(telephone.substring(1));
                }
                else
                {
                    this.children.add(new Node(telephone));
                }
            }
        }
    }
    public static void main(String args[])
    {
        System.out.println(getSolution(new Scanner(System.in))); // The number of elements (referencing a number) stored in the structure.
    }

     public static int getSolution(Scanner in)
     {
         Node root = new Node();
         int N = in.nextInt();
         for (int i = 0; i < N; i++) {
             String telephone = in.next();
             root.notContainedNumber(telephone);
         }
         return Node.numOfNodes;
     }
}

