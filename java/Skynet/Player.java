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
        /*Graph is undirected!*/
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        System.err.printf("N: %d, L: %d, E: %d\n", N, L, E);
        HashMap<Integer, Node> nodes = new HashMap();
        for (int i = 0; i < N; i++)
            nodes.put(i, new Node(i));
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            System.err.printf("Edge %d: %d %d\n", i, N1, N2);
            nodes.get(N1).addNeighbour(nodes.get(N2));
            nodes.get(N2).addNeighbour(nodes.get(N1));
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            System.err.printf("Gateway %d: %d\n", i, EI);
            nodes.get(EI).setGateway();
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            Pair bfsAns = bfs(nodes.get(SI));
            nodes.get(bfsAns.getX()).removeNeighbour(nodes.get(bfsAns.getY()));
            nodes.get(bfsAns.getY()).removeNeighbour(nodes.get(bfsAns.getX()));
            for (int i = 0; i < N; i++)
                nodes.get(i).setVisited(false);
            System.out.println(bfsAns.toString());
        }
    }

    public static Pair bfs(Node src)
    {
        // BFS uses Queue data structure
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(src);
        src.setVisited(true);
        while(!queue.isEmpty())
        {
            Node curr = queue.remove();
            for (Node child : curr.getNeighbours())
            {
                if (!child.isVisited())
                {
                    child.setVisited(true);
                    queue.add(child);
                }
                if (child.isGateway())
                    return new Pair(child.getVal(), curr.getVal());
            }
        }
        return new Pair(-1, -1);
    }

    static class Pair
    {
        private int x;
        private int y;

        Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX(){ return this.x;}

        public int getY(){ return this.y;}

        public String toString(){ return Integer.toString(this.x) + " " + Integer.toString(this.y);}

    }

    static class Node {
        private int val;
        private boolean visited;
        private boolean isGateway;
        LinkedList<Node> neighbours;

        Node(int val)
        {
            this.val = val;
            this.visited = false;
            this.neighbours = new LinkedList<Node>();
        }

        public int getVal(){return this.val;}

        public LinkedList<Node> getNeighbours(){return this.neighbours;}

        public boolean isVisited(){ return this.visited;}

        public void setVisited(boolean bool){ this.visited = bool;}

        public void setGateway(){ this.isGateway = true;}

        public boolean isGateway(){ return this.isGateway;}

        public void addNeighbour(Node neighbour){this.neighbours.add(neighbour);}

        public void removeNeighbour(Node neighbour){this.neighbours.remove(neighbour);}
    }
}