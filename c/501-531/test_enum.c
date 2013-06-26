#include <stdio.h>
int main()
{
	enum color {red=10, black=10, white};
	enum color r = red;
	printf("%d, %d, %d\n", r, black, white);
}