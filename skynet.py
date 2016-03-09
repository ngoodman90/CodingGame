import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

road = int(raw_input())  # the length of the road before the gap.
gap = int(raw_input())  # the length of the gap.
platform = int(raw_input())  # the length of the landing platform.

print >> sys.stderr, "road is ", road
print >> sys.stderr, "gap is ", gap
print >> sys.stderr, "platform is ", platform
k = 0
for max_speed in xrange(0, platform):
    k += max_speed
    if k >= platform:
        break
print >> sys.stderr, "max_speed:", max_speed
while True:
    speed = int(input())  # the motorbike's speed.
    coord_x = int(input())  # the position on the road of the motorbike.
        
    if (speed > max_speed):
        print("SLOW")
        print >> sys.stderr, 1
    elif (coord_x + speed + 1 < road and speed < max_speed):
        print("SPEED")
        print >> sys.stderr, 2
    elif (coord_x + speed + 1 == road):
        print("WAIT")
        print >> sys.stderr, 3
    elif (coord_x + speed  + 1 < road and speed >= max_speed):
        print("WAIT")
        print >> sys.stderr, 4
    elif (coord_x + speed == road):
        print("SLOW")
        print >> sys.stderr, 5
    elif (coord_x + speed > road and coord_x <= road):
        print("JUMP")
        print >> sys.stderr, 6
    elif (coord_x + 1 >= road + gap):
        print("SLOW")
        print >> sys.stderr, 7
    

    # Write an action using print
    # To debug: print >> sys.stderr, "Debug messages..."

    # A single line containing one of 4 keywords: SPEED, SLOW, JUMP, WAIT.

