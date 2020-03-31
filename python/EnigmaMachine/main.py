ALAPHABET = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'


def increment_str(s, n):
    return "".join([chr((ord(c) - ord('A') + i + n) % len(ALAPHABET) + ord('A')) for i, c in enumerate(s)])


def rotorise(s, rotor):
    return "".join([rotor[ALAPHABET.index(c)] for c in s])


def decrement_str(s, n):
    return "".join([chr((ord(c) - ord('A') - i - n) % len(ALAPHABET) + ord('A')) for i, c in enumerate(s)])


def reverse_rotorise(s, rotor):
    return "".join([ALAPHABET[rotor.index(c)] for c in s])


def encode(message, rotors, psuedo_rand):
    encoded = increment_str(message, psuedo_rand)
    for r in rotors:
        encoded = rotorise(encoded, r)
    return encoded


def decode(message, rotors, psuedo_rand):
    decoded = message
    for r in reversed(rotors):
        decoded = reverse_rotorise(decoded, r)
    decoded = decrement_str(decoded, psuedo_rand)
    return decoded


def main():
    operation = input()
    psuedo_rand = int(input())
    rotors = [input() for _ in range(3)]
    message = input()

    if operation == 'ENCODE':
        print(encode(message, rotors, psuedo_rand))
    elif operation == 'DECODE':
        print(decode(message, rotors, psuedo_rand))


if __name__ == "__main__":
    main()
