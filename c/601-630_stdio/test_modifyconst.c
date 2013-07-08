/**
 *  
 */
#include <stdio.h>
 int main()
 {
	/*-------------修改const常量--------------------*/
 	const int a=0;
 	int* b = &a;
 	*b = 1;
 	printf("%d\n", a);

 	/*-------------关键字的顺序--------------------*/
	static const int a2 = 1;//常用
	const static int a1 = 0;
	printf("a1: %d\n", a1);
	printf("a2: %d\n", a2);
	int static const a5 = 4;
	printf("a5: %d\n", a5);
	
	//const关键字放在类型或变量名之前等价的
	const int a3 = 2;//常用
	int const a4 = 3;
	printf("a3: %d\n", a3);
	printf("a4: %d\n", a4);

	//同上
	const int *p;    //same as below  const (int) * p
	int const *q;    // (int) const *p

	/*-------------* const, const *--------------------*/
	char tmp1 = 'a';
	char tmp2 = 'b';

	//p1 is a const pointer to char
	char * const p1 = &tmp1; //* 读成 pointer to
	printf("p1: %c\n", *p1);
	
	//不能改变指针指向
	//否则报错：assignment of read-only variable 'p1'
	//p1 = &tmp2;
	
	//可以改变值
	*p1 = 'd';
	printf("after modify p1: %c\n", *p1);

	//p2 is a pointer to const char; 
	const char * p2 = &tmp2; 
	
	//char const * p3;
	//同上因为C++里面没有const*的运算符，所以const只能属于前面的类型。
	
	//不能改变p2内容
	//否则报错：assignment of read-only location
	// *p2 = 'c';
	
	printf("p2: %c\n", *p2);
	
	//可以改变指向
	p2 = &tmp1;
	
	printf("p2: %c\n", *p2);
}