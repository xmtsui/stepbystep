#include <stdio.h>
int main()
{
	double x = 0.2;
	double y = 0.3;
	printf("%d\n", x==y);
	printf("%d\n", x-y<0.0000000000001);

	float x1 = 0.2;
	float y1 = 0.3;
	printf("%d\n", x1==y1);
	printf("%d\n", x1-y1<0.0000000000001);
}