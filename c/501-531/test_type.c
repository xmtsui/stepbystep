#include <stdint.h>
#include <stdio.h>
#include <complex.h>
int main()
{
	printf("%d\n", INT8_MIN);
	printf("int size=%d;\n", sizeof(1));
	printf("unsigned int size=%d;\n", sizeof(1U));
	printf("long size=%d;\n", sizeof(1L));
	printf("unsigned long size=%d;\n", sizeof(1UL));
	printf("long long size=%d;\n", sizeof(1LL));
	printf("unsigned long long size=%d;\n", sizeof(1ULL));

	printf("float complex size=%d\n", sizeof((float complex)1.0));
	printf("double complex size=%d\n", sizeof((double complex)1.0));
	printf("long double complex size=%d\n", sizeof((long double complex)1.0));

	return 0;
}