#include <stdio.h>


void func(int* n)
{
  *n = 20;
}

int func1(int n)
{
	n = 20;
	return n;
}

int main()
{
  int n = 10;
  //func(&n);
  n = func(n);
  printf("result => %d\n", n);
}


