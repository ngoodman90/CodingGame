# https://www.codingame.com/ide/puzzle/power-of-thor
x,y,w,z=map(int,input().split())
while True:
    d=""
    if z>y:d+='N';z-=1
    if z<y:d+='S';z+=1
    if w<x:d+='E';w+=1
    if w>x:d+='W';w-=1
    print(d)