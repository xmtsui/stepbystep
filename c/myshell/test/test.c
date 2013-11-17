#include <stdio.h>
#include <string.h>

main()
{
	int *a;
	int b = 0;
	a=&b;
	int c ;
	c=*a;
	printf("%d",c);
}
