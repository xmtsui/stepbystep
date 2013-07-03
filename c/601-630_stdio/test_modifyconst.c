#include <stdio.h>
int main()
{
	const int a=0;
	int* b = &a;
	*b = 1;
	printf("%d\n", a);
}