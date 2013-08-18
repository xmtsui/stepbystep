#include <stdio.h>
#define BIG_ENDIAN 0 
#define LITTLE_ENDIAN 1 
int TestByteOrder() {
	// short int word = 0x0001;
	// char *byte = (char *) &word;
	// return (byte[0] ? LITTLE_ENDIAN : BIG_ENDIAN);

	int word = 0x00000001;
	short *twobyte = (short *) &word;
	return (twobyte[0] ? LITTLE_ENDIAN : BIG_ENDIAN);
}

union w
{  
	int a;
	char b;
} c;

int TestByteOrder1() {
	c.a = 1;
	return (c.b ==1);
}

int main(){
	printf("%d\n", TestByteOrder());
	printf("%d\n", TestByteOrder1());

	char a=1;
	char *t = &a;
	printf("%ld\n", &a);//地址
	printf("%d\n", &a);//地址,溢出，只输出了低32位
	printf("%ld\n", &a+1);//地址加一个单位（单位为数据类型的大小)
	printf("%d\n", &a+1);//地址加一个单位（单位为数据类型的大小)
	printf("%ld\n", t);
	printf("%d\n", t);
	printf("%ld\n", t+1);
	printf("%d\n", t+1);
	printf("%ld\n", &t);
	return 1;
}