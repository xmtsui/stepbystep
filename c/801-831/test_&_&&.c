#include <stdio.h>
int main()
{
	if(1&1)
		printf("1&1: %d\n", 1&1);
	if(!(1&0))
		printf("!(1&0): %d\n", !(1&0));
	if(~(1&0))
		printf("~(1&0): %d\n", ~(1&0));
	return 0;
}