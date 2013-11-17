#ifndef _MT_PWD_H
#define _MT_PWD_H
#endif

#include <stdio.h>
#include <unistd.h>

char *mt_pwd(char *dirname)
{
	printf("%s\n",getcwd(dirname,MAX_SIZE));
	return dirname;

}
