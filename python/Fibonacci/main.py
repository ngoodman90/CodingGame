def gen_fib():
    a, b = 0, 1
    yield a
    yield b
    while True:
        a, b = b, a + b
        yield b


def fib(n):
    """
    0: 0, 1: 1, 2: 1, 3: 2, 5: 3, 6: 5, 7: 8
    """
    g = gen_fib()
    for _ in range(n - 1):
        next(g)
    return next(g)


if __name__ == "__main__":
    print(fib(int(input())))
