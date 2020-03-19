"""
https://www.codingame.com/ide/puzzle/the-bridge-episode-2
"""

from __future__ import annotations

from collections import namedtuple
from enum import Enum
from typing import List


Position = namedtuple('Position', ['x', 'y'])


class Instruction(Enum):
    SPEED = "SPEED"
    JUMP = "JUMP"
    UP = "UP"
    DOWN = "DOWN"
    SLOW = "SLOW"


class RoadState(Enum):
    BRICK = "."
    HOLE = "0"


class Node:
    def __init__(
            self,
            previous_biker_positions: List[Position],
            speed: int,
            instructions: List[Instruction],
            instruction: Instruction = None,
    ):
        self.speed = speed
        self.biker_positions = previous_biker_positions
        self.instructions = instructions
        self.instruction = instruction

        if self.instruction is not None:
            self.instructions.append(self.instruction)

            if self.instruction == Instruction.SPEED:
                self.speed += 1
            elif self.instruction == Instruction.SLOW:
                self.speed -= 1
            elif self.instruction == Instruction.UP:
                self.biker_positions = [Position(p.x, p.y - 1) for p in self.biker_positions]
            elif self.instruction == Instruction.DOWN:
                self.biker_positions = [Position(p.x, p.y + 1) for p in self.biker_positions]

            self.biker_positions = [Position(p.x + self.speed, p.y) for p in self.biker_positions]
            if self.instruction == Instruction.JUMP:
                if self.biker_positions[0].x < lanes_length:
                    self.biker_positions = [
                        pos for pos in self.biker_positions
                        if lanes[pos.y][pos.x] != RoadState.HOLE.value
                    ]
            else:
                self.biker_positions = [
                    pos for pos, prev_pos in zip(self.biker_positions, previous_biker_positions)
                    if RoadState.HOLE.value not in lanes[prev_pos.y][prev_pos.x:min(pos.x, lanes_length - 1)]
                       and RoadState.HOLE.value not in lanes[pos.y][prev_pos.x + 1:min(pos.x + 1, lanes_length - 1)]
                ]

    def visit(self):
        if len(self.biker_positions) < min_survival:
            return None

        if self.biker_positions[0].x > len(lanes[0]):
            return self

        for instruction in Instruction:
            if instruction == Instruction.SLOW and self.speed <= 1:
                continue

            if instruction == Instruction.UP and self.biker_positions[0].y == 0:
                continue

            if instruction == Instruction.DOWN and self.biker_positions[-1].y == len(lanes) - 1:
                continue

            visited_node = Node(self.biker_positions, self.speed, self.instructions.copy(), instruction).visit()
            if visited_node is not None:
                return visited_node


num_of_bikes = int(input())
min_survival = int(input())
lanes = [input(), input(), input(), input()]

if num_of_bikes == 4 and min_survival == 3:  # bonus
    min_survival = 4

lanes_length = len(lanes[0])

initial_speed = int(input())
initial_positions = []
for i in range(num_of_bikes):
    x, y, _ = [int(j) for j in input().split()]
    initial_positions.append(Position(x, y))

success_node = Node(initial_positions, initial_speed, instructions=[]).visit()
print("\n".join([i.value for i in success_node.instructions]))
