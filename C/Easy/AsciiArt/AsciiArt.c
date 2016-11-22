#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define ALPHABET_LENGTH 26
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int i, j, k;
    int L;
    scanf("%d", &L);
    int H;
    scanf("%d", &H); fgetc(stdin);
    char T[257];
    fgets(T, 257, stdin);
    char rows[H][1025];
    for (i = 0; i < H; i++) 
        fgets(rows[i], 1025, stdin);
    int diff;
    char ans[H][L*(strlen(T))];
    /*fprintf(stderr, "%d\n", strlen(T));*/
    for (i = 0; i < H; i++)
    {
        for(j=0; j < (strlen(T) - 1); j++)
        {
            diff = 0;
            if (65 <= T[j] && T[j] <= 90)
                diff = 65;
            else if (97 <= T[j] && T[j] <= 122)
                diff = 97;
            if (diff == 0)//char isnt a letter
            {
                for (int k = 0; k < L; k++)
                    printf("%c", rows[i][ALPHABET_LENGTH*L + k]);
            }
            else//char is a letter
            {
                for (int k = 0; k < L; k++)
                    printf("%c", rows[i][(T[j] - diff)*L + k]);
            }
        }
        printf("\n");
    }
    
    
    // Write an action using printf(). DON'T FORGET THE TRAILING \n
    // To debug: fprintf(stderr, "Debug messages...\n");
    return 0;
}