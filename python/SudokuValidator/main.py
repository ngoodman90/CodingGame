# https://www.codingame.com/training/easy/sudoku-validator

from itertools import chain

expected_values = list(range(1, 10))


def is_row_valid(row):
    return sorted(row) == expected_values


def squares(grid):
    yield grid[0][:3] + grid[1][:3] + grid[2][:3]
    yield grid[3][:3] + grid[4][:3] + grid[5][:3]
    yield grid[6][:3] + grid[7][:3] + grid[8][:3]
    yield grid[0][3:6] + grid[1][3:6] + grid[2][3:6]
    yield grid[3][3:6] + grid[4][3:6] + grid[5][3:6]
    yield grid[6][3:6] + grid[7][3:6] + grid[8][3:6]
    yield grid[0][6:9] + grid[1][6:9] + grid[2][6:9]
    yield grid[3][6:9] + grid[4][6:9] + grid[5][6:9]
    yield grid[6][6:9] + grid[7][6:9] + grid[8][6:9]


def columns(grid):
    for i in range(9):
        yield [row[i] for row in grid]


def is_grid_valid(grid):
    return all(is_row_valid(row) for row in chain(grid, columns(grid), squares(grid)))


grid = [list(map(int, input().split())) for _ in range(9)]

print(str(is_grid_valid(grid)).lower())
