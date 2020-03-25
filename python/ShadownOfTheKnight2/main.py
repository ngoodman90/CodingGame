import math
import operator
from dataclasses import dataclass
from typing import Iterator, Optional, Callable


@dataclass(frozen=True)
class Position:
    x: int
    y: int

    def distance(self, other):
        return math.sqrt((other.x - self.x)**2 + (other.y - self.y)**2)


def all_points():
    return (Position(i, j) for j in range(h) for i in range(w))


# game loop
def filter_points(
        possible_bomb_placements: Iterator[Position],
        curr_position: Position,
        prev_position: Optional[Position],
        op: Optional[Callable],
) -> Iterator[Position]:
    return (p for p in possible_bomb_placements
            if op(p.distance(curr_position),
                  p.distance(prev_position))
            )


if __name__ == "__main__":
    # w: width of the building.
    # h: height of the building.
    w, h = [int(i) for i in input().split()]
    n = int(input())  # maximum number of turns before game over.
    x0, y0 = [int(i) for i in input().split()]

    possible_bomb_placements = all_points()
    first_position = Position(x0, y0)


    while True:
        bomb_dir = input()  # Current distance to the bomb compared to previous distance (COLDER, WARMER, SAME or UNKNOWN)

        if bomb_dir == 'COLDER':
            op = operator.lt
        elif bomb_dir == 'WARMER':
            op = operator.gt
        elif bomb_dir == 'SAME':
            op = operator.eq

        possible_bomb_placements = filter_points(possible_bomb_placements, op)

        # Write an action using print
        # To debug: print("Debug messages...", file=sys.stderr)

        print("0 0")


def my_range(*args):
    if len(args) == 1:
        stop = args[0]
    elif len(args) == 2:
        start = args[0]
        stop = args[1]
    elif len(args) == 3:
        start = args[0]
        stop = args[1]
        step = args[2]
    if start is None:
        start = 0
    while start != stop:
        yield start
        start += step

