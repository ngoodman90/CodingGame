package com.teads;

/**
 * Created by ngoodman90 on 12/9/2015.
 */
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
/*        long startTime = System.currentTimeMillis();
        long currTime;*/

        Scanner in = new Scanner(System.in);
/*        int min_path = Integer.MAX_VALUE;
        int local_BFS;*/
        Map<Integer, LinkedList<Integer>> adjacency_list = new HashMap<Integer, LinkedList<Integer>>();

        initializeGraph(in, adjacency_list);
/*        Map<Integer, Integer> sizeList = new HashMap<>();
        for (Map.Entry<Integer, LinkedList<Integer>> vertex : adjacency_list.entrySet())
        {
            sizeList.put(vertex.getKey(), vertex.getValue().size());
        }
        for (Map.Entry<Integer, Integer> findLeaves : sizeList.entrySet())
        {
            if (findLeaves.getValue() == 1)
            {
                int pred = adjacency_list.get(findLeaves.getKey()).getFirst();
                sizeList.put(pred, sizeList.get(pred) - 1);
            }
        }*/

        /*currTime = System.currentTimeMillis();
        System.out.println("Initialization took "+(currTime - startTime) + " milliseconds");
        for (Map.Entry<Integer, LinkedList<Integer>> node : adjacency_list.entrySet())
        {
            int node_id = node.getKey();
            if (node.getValue().size() > 1)
            {
                local_BFS = BFS(adjacency_list, node_id, min_path);
                if (local_BFS < min_path)
                    min_path = local_BFS;
                currTime = System.currentTimeMillis();
                System.out.println("Took "+(currTime - startTime) + " milliseconds");
            }
        }*/
        int ans = removedLevels(adjacency_list);


        System.out.println(ans); // The minimal amount of steps required to completely propagate the advertisement
    }

    public static void initializeGraph
            (Scanner in,
             Map<Integer, LinkedList<Integer>> adjacency_list) {
        int n = in.nextInt(); // the number of adjacency relations
        for (int i = 0; i < n; i++) {
            int xi = in.nextInt(); // the ID of a person which is adjacent to yi
            int yi = in.nextInt(); // the ID of a person which is adjacent to xi
            if (!(adjacency_list.containsKey(xi))) {
                adjacency_list.put(xi, new LinkedList<Integer>());
            }
            adjacency_list.get(xi).add(yi);

            if (!(adjacency_list.containsKey(yi))) {
                adjacency_list.put(yi, new LinkedList<Integer>());
            }
            adjacency_list.get(yi).add(xi);
        }
    }

    public static int removedLevels(Map<Integer, LinkedList<Integer>> adjacency_list) {
        int steps = 0;
        LinkedList<Integer> removeVertices = new LinkedList<>();
        while ((adjacency_list.size() > 1)) {
            steps++;
            for (Map.Entry<Integer, LinkedList<Integer>> vertex : adjacency_list.entrySet()) {
                if (vertex.getValue().size() == 1) {
                    removeVertices.add(vertex.getKey());
                }
            }
            for (int removeVertex : removeVertices)
            {
                if (adjacency_list.size() > 1)
                {
                    int pred = adjacency_list.get(removeVertex).getFirst();
                    adjacency_list.get(pred).remove(adjacency_list.get(pred).indexOf(removeVertex));
                    adjacency_list.remove(removeVertex);
                }
            }
            removeVertices.clear();
        }
        return steps;
    }
}

 /*   public static int BFS(Map<Integer, LinkedList<Integer>> adjacency_list, int root, int min_path)
    {
*//*        System.out.println("root is: " + root);*//*
        int max_path = 0;
        LinkedList<Integer> vertex_queue = new LinkedList<Integer>();
        Map<Integer, Integer> distance_list = new HashMap<Integer, Integer>();


        for (Map.Entry<Integer, LinkedList<Integer>> vertex : adjacency_list.entrySet())
        {
            distance_list.put(vertex.getKey(), Integer.MAX_VALUE);
        }
        distance_list.put(root, 0);
        vertex_queue.add(root);

        while (!(vertex_queue.isEmpty()))
        {
            int curr_id = vertex_queue.removeFirst();
            for (int descendant_id : adjacency_list.get(curr_id))
            {
                if (distance_list.get(descendant_id) == Integer.MAX_VALUE)
                {
                    distance_list.put(descendant_id, distance_list.get(curr_id) + 1);
*//*                    System.out.println(descendant_id + " distance is: " + descendant_node.getNodeDistance());*//*
                    if (distance_list.get(descendant_id) > max_path)
                        max_path = distance_list.get(descendant_id);
                    if (max_path >= min_path)
                        return max_path;
                    vertex_queue.add(descendant_id);
                }
            }
        }
        return max_path;
    }
}*/


