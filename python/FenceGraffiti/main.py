"""
https://www.codingame.com/ide/puzzle/graffiti-on-the-fence
"""


def print_needs_painting(sorted_painted):
    i = 0
    missing = False
    for start, end in sorted_painted:
        if i < start:
            print(i, start)
            missing = True
        if i < end:
            i = end
    if i < len(sorted_painted):
        print(i, len(sorted_painted))
        missing = True
    if not missing:
        print("All painted")


def main():
    input()  # unused
    painted = [[int(j) for j in input().split()] for _ in range(int(input()))]
    sorted_painted = sorted(painted, key=lambda x: x[0])
    print_needs_painting(sorted_painted)


if __name__ == "__main__":
    main()
