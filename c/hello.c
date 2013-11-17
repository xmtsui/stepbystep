#include <stdio.h>
int main(void)
{
  int a = 1;
  int *b = &a;
  *b = 2;
  printf("%d\n", a);
}
