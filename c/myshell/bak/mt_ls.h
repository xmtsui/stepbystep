#ifndef _MT_LS_H
#define _MT_LS_H
#endif

#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>
#include <unistd.h>


void mt_ls(char *dirname,char *buf_cnv[])
{
	DIR *dir_ptr;
	int i = 0,j = 0; //i j is  counter 
	int mark = 0 ;
	struct dirent *direntp;
	while(buf_cnv[i] != NULL)
	{
		if((buf_cnv[i+1] == NULL) && (mark == 0))	
		{
			if((dir_ptr = opendir(dirname)) == NULL)
              		fprintf(stderr,"Can not open");
        		else
        		{
				while((direntp = readdir(dir_ptr)) != NULL)
				{
				if(direntp->d_name[0] == '.')
					j++; 
				else
				{	
				if(direntp->d_type == DT_DIR) 
				printf(BLUE"%s  "END_OF_COLOR,direntp->d_name);
				else if(direntp->d_type == DT_REG)
				printf("%s  ",direntp->d_name);
				else
				printf(RED"%s  "END_OF_COLOR,direntp->d_name);	
 				}
				}
			closedir(dir_ptr);
       			}
		printf("\n");
		mark = 1;
		}
		else if((mark == 0) && !(strncmp(buf_cnv[i+1],"-a",2))) 
		/*strncmp can not use NULL as a param,so mark == 0 should be putat  front*/
		{
			if((dir_ptr = opendir(dirname)) == NULL)
                        fprintf(stderr,"Can not open");
                        else
                        {
                                while((direntp = readdir(dir_ptr)) != NULL)
                                {
                                if(direntp->d_type == DT_DIR)
                                printf("\033[0;34m%s\033[0m  ",direntp->d_name);
                                else if(direntp->d_type == DT_REG)
                                printf("%s  ",direntp->d_name);
                                else
                                printf("\033[0;34m%s\033[0m  ",direntp->d_name); 
				}
			closedir(dir_ptr);
			}
		printf("\n");
		mark = 1;
		}
		else if(mark == 0 && !(strncmp("-F",buf_cnv[i+1],2)))
		{
			if((dir_ptr = opendir(dirname)) == NULL)
                        	fprintf(stderr,"Can not open");
                        else
                        {
                                while((direntp = readdir(dir_ptr)) != NULL)
                                {
                                if(direntp->d_name[0] == '.')
                                        j++;
                                else
                                {
                                if(direntp->d_type == DT_DIR)//type dir and then add "/" at the end of the file name
                                printf("\033[0;34m%s\033[0m/  ",direntp->d_name);
                                else if(direntp->d_type == DT_REG)
                                printf("%s  ",direntp->d_name);
                                else
                                printf("\033[0;34m%s\033[0m  ",direntp->d_name); 
                                }
				}
			closedir(dir_ptr);
			}
		printf("\n");
		mark = 1; 
		}	
		else if(mark == 0 && (buf_cnv[i+1] != NULL) && (strncmp("-",buf_cnv[i+1],1) != 0))
		{
			char *tmp;
			if(chdir(buf_cnv[i+1]) == -1) //end is not "\0"
			{
				printf("change failed\n");
			}
			getcwd(tmp,MAX_SIZE);
                        if((dir_ptr = opendir(tmp)) == NULL)
                        fprintf(stderr,"Can not open");
                        else
                        {
                                while((direntp = readdir(dir_ptr)) != NULL)
                                {
                                if(direntp->d_name[0] == '.')
                                        j++;
                                else
                                {      
                                if(direntp->d_type == DT_DIR)
                                printf("\033[0;34m%s\033[0m  ",direntp->d_name);
                                else if(direntp->d_type == DT_REG)
                                printf("%s  ",direntp->d_name);
                                else
                                printf("\033[0;34m%s\033[0m  ",direntp->d_name); 
                                }
                                }
                        closedir(dir_ptr);
                        }
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
