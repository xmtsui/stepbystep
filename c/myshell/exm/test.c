#include<stdio.h>
main()
{
	int array[] = {1,2,3,4} ;
	int *a ;
	a = array ;
	printf("%d\n",*(a+1));
}
