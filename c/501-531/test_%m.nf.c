#include <stdio.h>
int main()
{
	int i = 11;
	// int const *p = &i;
	const int *p = &i;
	//p++;
	// *p = 10;
	i=12;
	printf("%d\n", *p);
	printf("%6.2f\n",2.756);
	printf("%1d\n",123456);//%m.nf, m表示输出宽度，若定义宽度大于实际宽度，则前面补0，若小于则按实际宽度输出

	int *o, oo;
	printf("%d\n", oo);
	return 0;
}