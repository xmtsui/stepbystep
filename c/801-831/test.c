#include <stdio.h>
#include <string.h>
int main()
{
	int a[8] = {0};
	printf("%d\n", a[5]);
	printf("%d\n", a[7]);
	printf("%d\n", a[8]);
	// printf("%d\n", a[7000]);

	char s[10] = {0};
	printf("%d\n",sizeof(s));
	printf("%d\n",strlen(s));
}