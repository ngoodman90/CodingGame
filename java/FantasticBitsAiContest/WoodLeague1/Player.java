import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Grab Snaffles and try to throw them through the opponent's goal!
 * Move towards a Snaffle and use your team id to determine where you need to throw it.
 **/
class Player {

    private static int magicPoints;

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
        Bludger[] bludgers;
        int closestWizard;
        magicPoints = 0;
        

        // game loop
        while (true) {
            snaffles = new ArrayList<>(7);
            wizards = new Wizard[4];
            bludgers = new Bludger[2];
            if (magicPoints < 100)
                magicPoints++;
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
                            wizards[0] = new Wizard(entityType, entityId, new Point(x, y), new Point(vx, vy), state);
                        else
                            wizards[1] = new Wizard(entityType, entityId, new Point(x, y), new Point(vx, vy), state);
                        break;

                    case ("OPPONENT_WIZARD"):
                        if (wizards[2] == null)
                            wizards[2] = new Wizard(entityType, entityId, new Point(x, y), new Point(vx, vy), state);
                        else
                            wizards[3] = new Wizard(entityType, entityId, new Point(x, y), new Point(vx, vy), state);
                        break;

                    case ("BLUDGER"):
                        if (bludgers[0] == null)
                            bludgers[0] = new Bludger(entityType, entityId, new Point(x, y), new Point(vx, vy));
                        else
                            bludgers[1] = new Bludger(entityType, entityId, new Point(x, y), new Point(vx, vy));
                        break;

                    case ("SNAFFLE"):
                        snaffles.add(new Snaffle(entityType, entityId, new Point(x, y), new Point(vx, vy)));
                        break;    
                }           
            }
            
            closestWizard = bludgers[0].closestWizard(wizards);
            wizards[closestWizard].setBludger(bludgers[0]);
            closestWizard = bludgers[1].closestWizard(wizards);
            wizards[closestWizard].setBludger(bludgers[1]);

            System.err.println(wizards[0].toString());
            System.out.println(wizards[0].outputString(snaffles, goal, wizards[1]));
            System.out.println(wizards[1].outputString(snaffles, goal, wizards[0]));

            bludgers[0] = bludgers[1] = null;
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

        public int distanceToPoint(Point p)
        {
            int deltaX = this.x - p.getX();
            int deltaY = this.y - p.getY();
            return (int)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        }

        public String toString(){return this.getX() + " " + this.getY();}

    }
    
    public class Entity
    {   
        private String type;
        private int id;
        private Point position;
        private Point velocity;
        private Point nextPosition;
        
        public Entity(String type, int id, Point p, Point v)
        {
            this.type = type;
            this.id = id;
            this.position = p;
            this.velocity = v;
            this.nextPosition = null;
        }
        
        public int getId(){return this.id;}

        public Point getPosition(){return this.position;}
        
        public Point getVelocity(){return this.velocity;}
        
        public void nextLocation()
        {

        }

        public String toString()
        {
            String objectString = this.id + ", " + this.position.toString() + ", " + this.velocity.toString();
            return objectString;
        }
    }

    public class Wizard extends Entity
    {
        private int state;
        private Bludger closeBludger;
        private int distanceToBludger;

        public Wizard(String type, int id, Point p, Point v, int state)
        {
            super(type, id, p, v);
            this.state = state;
            this.closeBludger = null;
            distanceToBludger = Integer.MAX_VALUE;
        }
        
        public int getState(){return this.state;}

        public void setBludger(Bludger bludger)
        {
            this.closeBludger = bludger;
            this.distanceToBludger = this.getPosition().distanceToPoint(bludger.getPosition());
        }

        public Point findClosestSnaffle(ArrayList<Snaffle> snaffles, Wizard otherWizard)
        {
            if (snaffles.size() > 0)
            {
                if (snaffles.size() == 1)
                    return snaffles.get(0).getPosition();    
                int minDistance = this.getPosition().distanceToPoint(snaffles.get(0).getPosition());
                Snaffle closestSnaffle = snaffles.get(0);
                for (Snaffle snaffle : snaffles)
                {
                    int distanceToSnaffle = this.getPosition().distanceToPoint(snaffle.getPosition());
                    if (distanceToSnaffle < minDistance && otherWizard.getPosition().distanceToPoint(snaffle.getPosition()) > distanceToSnaffle)
                    {
                        minDistance = distanceToSnaffle;
                        closestSnaffle = snaffle;
                    }
                }
                snaffles.remove(closestSnaffle);
                return closestSnaffle.getPosition();
            }
            return new Point(8000, 3750);
        }

        public String outputString(ArrayList<Snaffle> snaffles, Point goal, Wizard otherWizard)
        {
            String output ="";
            if (this.distanceToBludger < 150 && magicPoints > Constants.OBLIVIATE_COST)
            {
                output = "OBLIVIATE" + " " + this.closeBludger.getId();
                magicPoints -= Constants.OBLIVIATE_COST;
            }
            else if (state == 0)
            {
                Point goTo = this.findClosestSnaffle(snaffles, otherWizard);
                int distanceToSnaffle = this.getPosition().distanceToPoint(goTo);
                int thrust = (distanceToSnaffle > 150 ? 150 : (int)distanceToSnaffle);
                output = "MOVE" + " " + goTo.toString() + " " + thrust;
            }
            else//wizard has a snaffle
            {
                int distanceToGoal = this.getPosition().distanceToPoint(goal);
                int power = (distanceToGoal > 500 ? 500 : (int) distanceToGoal);
                int y = this.getPosition().getY();
                int yGoalLine = (2400 < y && y < 5000 ? y : 3750);
                output = "THROW" + " " + goal.getX() + " " + yGoalLine + " " + power;
            }
            return output;
        }
    }

    public class Snaffle extends Entity
    {
        public Snaffle(String type, int id, Point p, Point v)
        {
            super(type, id, p, v);
        }        
    }

    public class Bludger extends Entity
    {

        private int closestWizard;
        private int distanceToWizard;

        public Bludger(String type, int id, Point p, Point v)
        {
            super(type, id, p, v);
            this.closestWizard = -1;
            this.distanceToWizard = -1;
        }   

        public int closestWizard(Wizard[] wizards)
        {
            int minDistance = this.getPosition().distanceToPoint(wizards[0].getPosition());
            int minIndex = 0;
            for (int i = 1; i < 4; i++)
            {
                int distanceToWizard = this.getPosition().distanceToPoint(wizards[i].getPosition());
                if (distanceToWizard < minDistance)
                {
                    this.distanceToWizard = distanceToWizard;
                    minDistance = distanceToWizard;
                    minIndex = i;
                }
            }
            this.closestWizard =  minIndex;
            return minIndex;
        }
    }

    public class Constants
    {
        //mass
        public static final int WIZARD_MASS = 1;
        public static final int BLUDGER_MASS = 8;
        public static final double SNAFFLE_MASS = 0.5;

        //friction
        public static final double WIZARD_FRICTION = 0.75;
        public static final double BLUDGER_FRICTION = 0.9;
        public static final double SNAFFLE_FRICTION = 0.75;

        //thrust
        public static final int BLUDGER_THRUST = 1000;

        //spell costs
        public static final int OBLIVIATE_COST = 5;
        public static final int PETRIFICUS_COST = 10;
        public static final int ACCIO_COST = 20;
        public static final int FLIPENDO_COST = 20;
    }
}