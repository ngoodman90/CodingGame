import math
from typing import List, Tuple


def distance(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)


def tsp(points: List[Tuple[int, int]]) -> float:
    total_distance = 0.0
    first_point = curr_point = points.pop(0)
    while points:
        next_point = min(points, key=lambda p: distance(curr_point, p))
        total_distance += distance(curr_point, next_point)
        curr_point = next_point
        points.remove(next_point)
    total_distance += distance(curr_point, first_point)
    return total_distance


def main():
    points = [tuple(map(int,  input().split())) for _ in range(int(input()))]
    print(round(tsp(points)))


if __name__ == "__main__":
    main()
