def fib(n):
    a = 0
    b = 1
    for _ in range(n):
        a, b = b, a + b
    return a


if __name__ == "__main__":
    print(fib(int(input())))
