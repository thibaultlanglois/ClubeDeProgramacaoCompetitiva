#include <stdio.h>

int rememb[10000];

int cycle_len(int n)
{
    int len;

    len = 1;
    if (n == 1)
        return (1);
    if (n < 10000 && rememb[n])
         return (rememb[n]);
    while (n > 1) {
        if (n < 10000 && rememb[n]) {
             len += rememb[n];
             break;
        }
        len += 1;
        if (n % 2 == 1) {
            n = 3 * n + 1;
        } else {
            n /= 2;
        }
    }
    rememb[n] = len;
    return (len);
}

int main(int argc, char **argv)
{
    int max_len;
    int new_len;
    int i;
    int j;
    int max;
    int min;

    while (scanf("%d %d", &i, &j) != EOF) {
        max_len = 0;
        max = (i > j) ? i : j;
        min = (i > j) ? j : i;
        printf("%d %d ", i, j);
        while (min <= max) {
            new_len = cycle_len(min);
            if (new_len > max_len)
                max_len = new_len;
            min++;
        }
        printf("%d\n", max_len);
    }
    return (0);
}
