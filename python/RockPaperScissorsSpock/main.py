# https://www.codingame.com/ide/puzzle/rock-paper-scissors-lizard-spock

from collections import defaultdict

rules = {
    'C': ['P', 'L'],
    'P': ['R', 'S'],
    'R': ['L', 'C'],
    'L': ['S', 'P'],
    'S': ['C', 'R']
}


def winner(pl1, pl2):
    pl1_wins = pl2[1] in rules[pl1[1]]
    pl2_wins = pl1[1] in rules[pl2[1]]

    if pl1_wins and not pl2_wins:
        return pl1, pl2
    if pl2_wins and not pl1_wins:
        return pl2, pl1
    return (pl1, pl2) if pl1[0] < pl2[0] else (pl2, pl1)


opponents = defaultdict(list)
n = int(input())
games = []
for i in range(n):
    numplayer, signplayer = input().split()
    games.append((int(numplayer), signplayer))

while len(games) > 1:
    next_games = []
    for p1, p2 in zip(games[::2], games[1::2]):
        w, l = winner(p1, p2)
        next_games.append(w)
        opponents[w[0]].append(l[0])
    games = next_games

print(games[0][0])
print(*opponents[games[0][0]])
