#include <stdio.h>
#include <wchar.h>
int main()
{
	int i1 = 010;//八进制
	int j1 = 0x0a;//16进制= 0x0A
	printf("%d, %d\n", i1, j1);

	/*常量类型很重要,可以通过后缀来区分类型*/
	int a = 0x200;    //-> int
	int b = 200U;     //-> unsigned int
	int c = 0L;       //-> long
	int d = 0xf0f0UL; //-> unsigned long
	int e = 0777LL;   //-> long long
	int f = 0xFFULL;  //-> unsigned long long

	printf("%d,%d,%d,%d,%d,%d\n", a,b,c,d,e,f);

	/*可以用十进制或十六进制表示浮点数常量*/
	/*默认浮点常量是 double,可以⽤用 F 后缀表⽰示 float,⽤用 L 后缀表⽰示 long double 类型*/
	float aa = 10.0;   	//-> 10
	float bb = 10.;    	//-> 10
	float cc = .123;   	//-> 0.123
	float dd = 2.34E5;	//-> 2.34 * (10 ** 5)
	float ee = 67e-12;	//-> 67.0 * (10 ** -12)
	printf("%f,%f,%f,%f,%f\n", aa,bb,cc,dd,ee);

	/*字符常量默认是 int 类型,除⾮非⽤用前置 L 表⽰示 wchar_t 宽字符类型*/
	char c1 = 0x61;
	char c2 = 'a';
	char c3 = '\x61';
	printf("%c, %c, %c, %d\n", c1, c2, c3, 0x61);
}