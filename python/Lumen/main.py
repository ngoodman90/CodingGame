# https://www.codingame.com/ide/puzzle/lumen
def shed_light(n, i, j, lumens, dark):
    for a in range(max(i - lumens + 1, 0), min(i + lumens, n)):
        for b in range(max(j - lumens + 1, 0), min(j + lumens, n)):
            dark[a][b] = 0


def find_lit_candles(n, lumens, lines, dark):
    for i in range(n):
        for j in range(n):
            if lines[i][j] == 'C':
                shed_light(n, i, j, lumens, dark)


def main():
    n = int(input())
    lumens = int(input())
    lines = [input().split() for _ in range(n)]
    dark = [[1] * n for _ in range(n)]
    find_lit_candles(n, lumens, lines, dark)
    print(sum(map(sum, dark)))


if __name__ == "__main__":
    main()
