#ifndef _MT_MKDIR_H
#define _MT_MKDIR_H
#endif

#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>

void mt_mkdir(char *dirname,char *buf_cnv[])
{
	int z,i = 0;
	if(buf_cnv[i+1] == NULL)
	{
		printf("MISSING ARGUMENT\n");
		return;
	}
	if(strncmp(buf_cnv[i+1],"/",1) != 0)  //create dir in the current path
	{
		strcat(dirname,"/");
		strcat(dirname,buf_cnv[i+1]);
		z = mkdir(dirname,S_IRWXU|S_IRWXG|S_IROTH|S_IXOTH)
; // 0775

		if(z == -1)
		fprintf(stderr,"mkdir fail\n");
	}
	else   //create dir in  a  certain  path
	{
		z = mkdir(buf_cnv[i+1],S_IRWXU|S_IRWXG|S_IROTH|S_IXOTH);
		if(z == -1)
       		printf("mkdir failed\n");
	
	}

}  
	 
