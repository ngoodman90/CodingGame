
import java.awt.*;
import java.util.*;
import java.util.Map;

class Player
{
    public static int R, C, A;
    public static boolean reachedControlRoom = false;
    public static boolean foundControlRoom = false;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        R = in.nextInt(); // number of rows.
        C = in.nextInt(); // number of columns.
        A = in.nextInt(); // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.

        // game loop
        while (true) {
            String tempString;
            ArrayList<String> chars = new ArrayList<>();
            int KR = in.nextInt(); // row where Kirk is located.
            int KC = in.nextInt(); // column where Kirk is located.
            Node currentLocation = new Node(new Point(KR, KC), "");
            for (int i = 0; i < R; i++) {
                tempString = in.next();
                chars.add(tempString); // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
            }
            if (chars.get(KR).charAt(KC) == 'C')
                reachedControlRoom = true;
            if (reachedControlRoom)
                bfs(chars, currentLocation, 'T');
            else if (foundControlRoom)
                bfs(chars, currentLocation, 'C');
            else
                bfs(chars, currentLocation, '?');
        }
    }

    public static void bfs(ArrayList<String> chars, Node src, char dest)
    {
        Map<Point, Boolean> visited = new HashMap<>();
        Queue queue = new LinkedList();
        Node current = null;
        queue.add(src);
        while(!queue.isEmpty())
        {
            current = (Node)queue.remove();
            int x = (int)current.getPoint().getX();
            int y = (int)current.getPoint().getY();
            String direction = current.getDirection();
            char currentChar =chars.get(x).charAt(y);
            if (currentChar == 'C' && !foundControlRoom)
                foundControlRoom = true;
            if (visited.containsKey(current.getPoint()))
                continue;
            if (currentChar == dest)
                break;
            if (currentChar == '?' || currentChar == '#')
                continue;
            if (direction.equals(""))
            {
                queue.add(new Node(new Point(x - 1, y), "UP"));
                queue.add(new Node(new Point(x, y + 1), "RIGHT"));
                queue.add(new Node(new Point(x, y - 1), "LEFT"));
                queue.add(new Node(new Point(x + 1, y), "DOWN"));
            }
            else
            {
                queue.add(new Node(new Point(x - 1, y), direction));
                queue.add(new Node(new Point(x, y + 1), direction));
                queue.add(new Node(new Point(x, y - 1), direction));
                queue.add(new Node(new Point(x + 1, y), direction));
            }
            visited.put(current.getPoint(), true);
        }
        System.out.println(current.getDirection());
    }

    static class Node {
        private Point point;
        private String direction;

        Node(Point point, String d) {
            this.point = point;
            this.direction = d;
        }

        public Point getPoint() {
            return this.point;
        }

        public String getDirection() {
            return this.direction;
        }

        public String nodeToString(){
            return "X: " + this.point.getX() + ", Y: " + this.point.getY();
        }
    }

}
