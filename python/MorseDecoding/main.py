# https://www.codingame.com/ide/puzzle/the-resistance

MORSE = {
    'A': '.-', 'B': '-...',
    'C': '-.-.', 'D': '-..', 'E': '.',
    'F': '..-.', 'G': '--.', 'H': '....',
    'I': '..', 'J': '.---', 'K': '-.-',
    'L': '.-..', 'M': '--', 'N': '-.',
    'O': '---', 'P': '.--.', 'Q': '--.-',
    'R': '.-.', 'S': '...', 'T': '-',
    'U': '..-', 'V': '...-', 'W': '.--',
    'X': '-..-', 'Y': '-.--', 'Z': '--..'
}


def find_prefix_match(current_words, suffix):
    if len(suffix) == 0:
        ans.append(current_words)
    for w in morse_words:
        if suffix.startswith(w):
            current_words.append(w)
            find_prefix_match(current_words, suffix[len(w):])


l = input()
n = int(input())
words = [input() for _ in range(n)]
morse_words = [''.join(MORSE[c] for c in w) for w in words]
ans = []
find_prefix_match([], l)
print(len(ans))


