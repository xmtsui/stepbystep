#ifndef _MT_RMDIR_H
#define _MT_RMDIR_H
#endif

#include <unistd.h>
#include <sys/types.h>
#include <dirent.h>

void mt_rmdir(char *dirname,char *buf_cnv[])
{
	int i = 0; //counter
	int z = 0; //use as a judge mark
	if(buf_cnv[i+1] == NULL)
	{
		printf("MISSING ARGUMENTS");
		return;
	}
	else  
	{
	  if(strncmp(buf_cnv[i+1],"/",1) != 0)  //delete dir in the current path
          {
                strcat(dirname,"/");
                strcat(dirname,buf_cnv[i+1]);
		if(opendir(dirname) != NULL)
		{
    			printf("directory not empty!\n");
			return;
		}
		else
		{
                z = rmdir(dirname);
                if(z == -1)
                fprintf(stderr,"rmdir fail\n");
		}
          }
          else   // delete empty dir in  a  certain  path
          {
                z = rmdir(buf_cnv[i+1]);
                if(z == -1)
                printf("rmdir failed\n");

          }
	}
}
		
