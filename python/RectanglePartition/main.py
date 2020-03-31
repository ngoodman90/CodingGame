"""
https://www.codingame.com/training/easy/rectangle-partition
"""


def get_deltas(int_list):
    deltas = []
    for i in range(len(int_list)):
        for j in range(i):
            deltas.append(int_list[i] - int_list[j])
    return deltas


def get_num_of_squares(x_split, y_split):
    dx = get_deltas(x_split)
    dy = get_deltas(y_split)

    return sum(dy.count(d) for d in dx)


def get_input():
    return map(int, input().split())


def main():
    w, h, _, _ = get_input()
    x_split = [0] + list(get_input()) + [w]
    y_split = [0] + list(get_input()) + [h]

    print(get_num_of_squares(x_split, y_split))


if __name__ == "__main__":
    main()
