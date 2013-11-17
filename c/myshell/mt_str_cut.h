#ifndef _MT_STR_CUT_H
#define _MT_STR_CUT_H
#endif

#include <stdio.h>
void str_cut(char *p,char *buf[])
{
	int i=0,j=0; //index of array 
	int  u=0; //value false when former letter is blank 
	
	while(*p)
	{
		if(*p==' ')
		{
			*p='\0';//replace blank to end-mark
			p++;
			u=0;
		}
		else
		{
			if(u==0)
			{
				buf[i++]=p++;
				u=1;
			}
			else
			p++;
		}
	}
	buf[i] = NULL;	//set end of buf NULL     
}	
 
