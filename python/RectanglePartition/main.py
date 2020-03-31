def get_num_of_squares(x_split, y_split):
    dx = []
    dy = []
    for i in range(len(x_split)):
        for j in range(i):
            dx.append(x_split[i] - x_split[j])
    for i in range(len(y_split)):
        for j in range(i):
            dy.append(y_split[i] - y_split[j])

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
