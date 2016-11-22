#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
int cmpfunc (const void * a, const void * b){return ( *(int*)a - *(int*)b );}

int main()
{
    /*Constraints:
        N, x, y can be ints
        L can be long*/
    
    int N;
    int min = INT_MAX;
    int max = INT_MIN;
    int median;
    long ans = 0;
    scanf("%d", &N);
    fprintf(stderr, "N: %d\n", N);
    int array[N];
    for (int i = 0; i < N; i++) {
        int X;
        int Y;
        scanf("%d%d", &X, &Y);
        fprintf(stderr, "X: %d, Y: %d\n", X, Y);
        array[i] = Y;
        if (X < min)
            min = X;
        if (X > max)
            max = X;
    }
    
    qsort(array, N, sizeof(int), cmpfunc);
    median = array[N / 2];
    ans  += max - min;//length of main cable
    
    for (int i = 0; i < N; i++)
        ans += abs(array[i] - median);
    
    
    
    
    // Write an action using printf(). DON'T FORGET THE TRAILING \n
    // To debug: fprintf(stderr, "Debug messages...\n");
    printf("%ld\n", ans);

    return 0;
}