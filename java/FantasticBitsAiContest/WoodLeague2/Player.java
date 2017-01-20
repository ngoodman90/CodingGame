import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Grab Snaffles and try to throw them through the opponent's goal!
 * Move towards a Snaffle and use your team id to determine where you need to throw it.
 **/
class Player {

    public static void main(String args[]) {
        Player player = new Player();
        player.start();
        
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        int myTeamId = in.nextInt(); // if 0 you need to score on the right of the map, if 1 you need to score on the left
        Point goal = (myTeamId == 0 ? new Player.Point(16000, 3750) : new Player.Point(0, 3750));
        Wizard[] wizards;
        ArrayList<Snaffle> snaffles;
        Bludger bludger1 = null;
        Bludger bludger2 = null;
        

        // game loop
        while (true) {
            snaffles = new ArrayList<>(7);
            wizards = new Wizard[4];
            int entities = in.nextInt(); // number of entities still in game
            for (int i = 0; i < entities; i++) {
                int entityId = in.nextInt(); // entity identifier
                String entityType = in.next(); // "WIZARD", "OPPONENT_WIZARD" or "SNAFFLE" (or "BLUDGER" after first league)
                int x = in.nextInt(); // position
                int y = in.nextInt(); // position
                int vx = in.nextInt(); // velocity
                int vy = in.nextInt(); // velocity
                int state = in.nextInt(); // 1 if the wizard is holding a Snaffle, 0 otherwise
                switch (entityType)
                {
                    case ("WIZARD"):
                        if (wizards[0] == null)
                            wizards[0] = new Wizard(entityId, new Point(x, y), new Point(vx, vy), state);
                        else
                            wizards[1] = new Wizard(entityId, new Point(x, y), new Point(vx, vy), state);
                        break;

                    case ("OPPONENT_WIZARD"):
                        if (wizards[2] == null)
                            wizards[2] = new Wizard(entityId, new Point(x, y), new Point(vx, vy), state);
                        else
                            wizards[3] = new Wizard(entityId, new Point(x, y), new Point(vx, vy), state);
                        break;

                    case ("BLUDGER"):
                        if (bludger1 == null)
                            bludger1 = new Bludger(entityId, new Point(x, y), new Point(vx, vy));
                        else
                            bludger2 = new Bludger(entityId, new Point(x, y), new Point(vx, vy));
                        break;

                    case ("SNAFFLE"):
                        snaffles.add(new Snaffle(entityId, new Point(x, y), new Point(vx, vy)));
                        break;    
                }           
            }
            System.out.println(wizards[0].outputString(snaffles, goal));
            System.out.println(wizards[1].outputString(snaffles, goal));
            bludger1 = bludger2 = null;
        }
    }

    public class Point
    {
        private int x;
        private int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX(){return this.x;}

        public int getY(){return this.y;}

        public double distanceToPoint(Point p)
        {
            int deltaX = this.x - p.getX();
            int deltaY = this.y - p.getY();
            return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        }

        public String toString(){return this.getX() + " " + this.getY();}

    }
    
    public class Entity
    {
        private int id;
        private Point position;
        private Point velocity;
        
        public Entity(int id, Point p, Point v)
        {
            this.id = id;
            this.position = p;
            this.velocity = v;
        }
        
        public int getId(){return this.id;}
        public Point getPosition(){return this.position;}
        public Point getVelocity(){return this.velocity;}
    }

    public class Wizard extends Entity
    {
        private int state;

        public Wizard(int id, Point p, Point v, int state)
        {
            super(id, p, v);
            this.state = state;
        }
        
        public int getState(){return this.state;}

        public Point findClosestSnaffle(ArrayList<Snaffle> snaffles)
        {
            double minDistance = this.getPosition().distanceToPoint(snaffles.get(0).getPosition());
            Snaffle closestSnaffle = snaffles.get(0);
            for (Snaffle snaffle : snaffles)
            {
                double distanceToSnaffle = this.getPosition().distanceToPoint(snaffle.getPosition());
                if (distanceToSnaffle < minDistance)
                {
                    minDistance = distanceToSnaffle;
                    closestSnaffle = snaffle;
                }
            }
            snaffles.remove(closestSnaffle);
            return closestSnaffle.getPosition();
        }

        public String outputString(ArrayList<Snaffle> snaffles, Point goal)
        {
            String output ="";
            if (state == 0)
            {
                Point goTo = this.findClosestSnaffle(snaffles);
                double distanceToSnaffle = this.getPosition().distanceToPoint(goTo);
                int power = (distanceToSnaffle > 150 ? 150 : (int)distanceToSnaffle);
                output = "MOVE" + " " + goTo.toString() + " " + power;
            }
            else//wizard has a snaffle
            {
                double distanceToGoal = this.getPosition().distanceToPoint(goal);
                int power = (distanceToGoal > 500 ? 500 : (int) distanceToGoal);
                int y = this.getPosition().getY();
                int yGoalLine = (y > 5450 ? 5450 : (y > 2050 ? y : 2050));
                output = "THROW" + " " + goal.getX() + " " + yGoalLine + " " + power;
            }
            return output;
        }
    }

    public class Snaffle extends Entity
    {
        public Snaffle(int id, Point p, Point v)
        {
            super(id, p, v);
        }        
    }

    public class Bludger extends Entity
    {
        public Bludger(int id, Point p, Point v)
        {
            super(id, p, v);
        }   
    }
}