# https://www.codingame.com/ide/puzzle/temperature-code-golf
input()
a=list(map(int,input().split()))or[0]
t=min(map(abs,a))
print((-1*t,t)[t in a])