#include <iostream>
#include <stdio.h>
using namespace std;
int main(){
	int *columns1 = new int[10];
	cout<<columns1<<endl;
	printf("%lx\n", columns1);
	delete []columns1;//释放指针分配的数组，如果不加[]表示释放指针

	const int &d=1;//引用值的话，只能引用常量值
	printf("%d\n", d);

	typedef double (*DBL)[10];//
	DBL test;
	printf("%ld\n", sizeof test);

	int columns[] = {0,1,2,3};
	int *column = columns;
	// 等同于int *columns，或者int columns[10]
	printf("%ld\n", sizeof columns);
	printf("%ld\n", sizeof column);
	// printf("%d\n", sizeof a);
	// printf("%d\n", *t);
}