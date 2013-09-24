#include <stdio.h>
struct A{
	int a;
	short b;
	int c;
	char d;
};

struct B{
	int a;
	short b;
	char d;
	int c;
};

int main()
{
	printf("%ld\n", sizeof(struct A));
	printf("%ld\n", sizeof(struct B));
	return 0;
}