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
            Node gateway = nodes.get(EI);
            gateway.setGateway();
            gateway.getNeighbours().forEach(node->node.incrementGatewayNeighbourNum());
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            PriorityQueue<Node> queue = new PriorityQueue<>(N, (x,y) -> x.getDistance() - y.getDistance());
            Pair ans;
            Node x, y;
            nodes.get(SI).setDistance(0);
            nodes.forEach((num, node) -> queue.add(node));

            ans = djikstra(queue);
            x = nodes.get(ans.getX());
            y = nodes.get(ans.getY());
            System.err.println("X: " + x.toString());
            System.err.println("Y: " + y.toString());
            x.removeNeighbour(y);
            y.removeNeighbour(x);
            x.decrementGatewayNeighbourNum();
            nodes.forEach((i, node)->{
                node.setVisited(false);
                node.setDistance(Integer.MAX_VALUE);
            });
            System.out.println(ans.toString());
        }
    }

    public static Pair djikstra(PriorityQueue<Node> queue)
    {
        Pair ans = new Pair(-1, -1);
        int currentLinkPriority;
        int priorityLink = Integer.MAX_VALUE;
        while(!queue.isEmpty())
        {
            Node curr = queue.remove();
            System.err.println("Distance: " + curr.getDistance());
            curr.setVisited(true);
            for (Node child : curr.getNeighbours())
            {
                if (!child.isVisited())
                {
                    queue.remove(child);
                    if (curr.getNumOfGatewayNeighbours() > 0)
                        child.setDistance(curr.getDistance() < child.getDistance() ?
                                curr.getDistance() : child.getDistance());
                    else
                        child.setDistance(curr.getDistance() + 1 < child.getDistance() ?
                                curr.getDistance() + 1 : child.getDistance());

                    if (child.isGateway())
                    {
                        currentLinkPriority = child.getDistance() - curr.getNumOfGatewayNeighbours();

                        if (currentLinkPriority < 0)
                            return new Pair(curr.getVal(), child.getVal());

                        if (currentLinkPriority < priorityLink)
                        {
                            priorityLink = currentLinkPriority;
                            ans = new Pair(curr.getVal(), child.getVal());
                        }
                    }
                    queue.add(child);
                }
            }
        }
        return ans;
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
        private int distance;
        private int numOfGatewayNeighbours;
        private LinkedList<Node> neighbours;

        Node(int val)
        {
            this.val = val;
            this.visited = false;
            this.distance = Integer.MAX_VALUE;
            this.numOfGatewayNeighbours = 0;
            this.neighbours = new LinkedList<>();
        }

        public int getVal(){return this.val;}

        public int getDistance(){return distance;}

        public void setDistance(int distance){this.distance = distance;}

        public int getNumOfGatewayNeighbours() {return numOfGatewayNeighbours;}

        public void incrementGatewayNeighbourNum() {this.numOfGatewayNeighbours++;}

        public void decrementGatewayNeighbourNum() {this.numOfGatewayNeighbours--;}

        public LinkedList<Node> getNeighbours(){return this.neighbours;}

        public boolean isVisited(){ return this.visited;}

        public void setVisited(boolean bool){ this.visited = bool;}

        public void setGateway(){ this.isGateway = true;}

        public boolean isGateway(){ return this.isGateway;}

        public void addNeighbour(Node neighbour){this.neighbours.add(neighbour);}

        public void removeNeighbour(Node neighbour){this.neighbours.remove(neighbour);}

        public String toString()
        {
            if (this.isGateway)
                return "Val: " + this.val + " Distance: " + this.distance 
                    + " Num of neighbours: " + this.neighbours.size();
            else
                return "Val: " + this.val + " Distance: " + this.distance 
                    + " Gateway neighbours: " + this.numOfGatewayNeighbours; 
        }
    }
}