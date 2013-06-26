#include <string.h>
#include <stdio.h>
/*标准c无重载*/
int main()
{
	int a=1;
	char* s="str";
	say(s);
	// say(a);
}

void say(char* str)
{
	int len=sizeof(str);
	int i=0;
	for(i=0; i<len; ++i)
		printf("%c\n", str[i]);
}