#ifndef _MT_LS_H
#define _MT_LS_H
#endif

#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

#include "mt_alphasort.h"

void mt_ls(char *dirname,char *buf_cnv[])
{
	//DIR *dir_ptr;
	int i = 0,j = 0; //i j is  counter 
	int mark = 0 ;   //ensure every option be executed once
	struct dirent *direntp;
	while(buf_cnv[i] != NULL)
	{
		if((buf_cnv[i+1] == NULL) && (mark == 0))//ls .	
		{
			mt_alphasort(dirname,1);
			printf("\n");
       		        mark = 1;
		}
		else if( (mark == 0) && !(strncmp(buf_cnv[i+1],"--help",6)) )
        	{
                printf("usage is: %s [--help | -a | -F ] [DIRECTORY]\n", buf_cnv[0]);
                mark = 1; 

        	}

		else if((mark == 0) && !(strncmp(buf_cnv[i+1],"-a",2)))//ls -a 
		/*strncmp can not use NULL as a param,so mark == 0 should be putat  front*/
		{
			mt_alphasort(dirname,2);
			printf("\n");
			mark = 1;
		}
		else if(mark == 0 && !(strncmp("-F",buf_cnv[i+1],2)))//ls -F
		{
			mt_alphasort(dirname,3);
			printf("\n");
			mark = 1; 
		}	
		else if(mark == 0 && (buf_cnv[i+1] != NULL) && (strncmp("-",buf_cnv[i+1],1) != 0)) //ls <path>
		{
			mt_alphasort(buf_cnv[i+1],1);//use alphasort type 1
			printf("\n");
			mark = 1;
		}
		else if( mark == 0 )
		{
			printf("This Option has not been enabled\n");
			mark = 1;
		}
		i++;
	}
}
