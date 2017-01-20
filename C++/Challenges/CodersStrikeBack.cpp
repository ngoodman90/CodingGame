#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;


class Point
{
    private:
        int x, y;
    public:
        Point() : x(0), y(0) {}
        Point(int x, int y) : x(x), y(y) {}
        
/*        //getter
        int getX();
        int getY();*/
        
        int getX(){ return x;} 
        int getY(){ return y;} 

};

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    bool boost = false;
    vector<Point> checkpoints_vector; 
    Point checkpoint;
    // game loop
    while (1) {
        int x;
        int y;
        int nextCheckpointX; // x position of the next check point
        int nextCheckpointY; // y position of the next check point
        int nextCheckpointDist; // distance to the next checkpoint
        int nextCheckpointAngle; // angle between your pod orientation and the direction of the next checkpoint
        cin >> x >> y >> nextCheckpointX >> nextCheckpointY >> nextCheckpointDist >> nextCheckpointAngle; cin.ignore();
        int opponentX;
        int opponentY;
        cin >> opponentX >> opponentY; cin.ignore();

        // Write an action using cout. DON'T FORGET THE "<< endl"
        // To debug: cerr << "Debug messages..." << endl;
        
        int thrust;
        if (nextCheckpointAngle > 90 || nextCheckpointAngle < -90)
            thrust = 0;
        else
            thrust = max(0, min(100, nextCheckpointDist) - abs(nextCheckpointAngle));

    
        // You have to output the target position
        // followed by the power (0 <= thrust <= 100)
        // i.e.: "x y thrust"
        if (!boost)
        {
            boost = true;
            cout << nextCheckpointX << " " << nextCheckpointY << " BOOST" << endl;
        }
        else
            cout << nextCheckpointX << " " << nextCheckpointY << " " << thrust << endl;
    }
}