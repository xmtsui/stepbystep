#ifndef _MT_RM_H
#define _MT_RM_H
#endif

#include <unistd.h>

void mt_rm(char *dirname,char *buf_cnv[])
{
	int z,i = 0;
        if(buf_cnv[i+1] == NULL)
        {
                printf("MISSING ARGUMENT\n");
                return;
        }
	if(strncmp(buf_cnv[i+1],"--help",6) == 0)
	{
		printf("usage is: %s [--help | -r ] [filename]\n", buf_cnv[0]);
		return;
	} 
        if(strncmp(buf_cnv[i+1],"/",1) != 0)
        {
        	strcat(dirname,"/");
        	strcat(dirname,buf_cnv[i+1]);
        	z = unlink(dirname);
        	if(z == -1)
		{
	        printf("remove failed\n");
		return;
       		}
	} 
        else
        {
        z = unlink(buf_cnv[i+1]);
        if(z == -1)
        printf("remove failed\n");
        }
}

