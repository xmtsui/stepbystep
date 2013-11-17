#ifndef _MT_ALPHASORT_H
#define _MT_ALPHASORT_H
#endif

#include <stdlib.h>
#include <dirent.h>

void mt_alphasort(char *dirname,int i)
{
	   struct dirent **direntp;
           int n,j;
           n = scandir(dirname, &direntp, 0, alphasort);
           if (n < 0)
               perror("scandir");
           else 
		{
               		while (n--) 
			{
			if(i == 1)   //ls local dir
			{
			   	if(direntp[n]->d_name[0] == '.')
                                        j++;
                                else
                                {
                                if(direntp[n]->d_type == DT_DIR)
                                printf(BLUE"%s  "END_OF_COLOR,direntp[n]->d_name);
                                else if(direntp[n]->d_type == DT_REG)
                                printf("%s  ",direntp[n]->d_name);
                                else
                                printf(RED"%s  "END_OF_COLOR,direntp[n]->d_name);
                                }
			} //end of if (i == 1)
			if(i == 2)   //ls local dir with option -a
			{
			 	if(direntp[n]->d_type == DT_DIR)
                                printf(BLUE"%s  "END_OF_COLOR,direntp[n]->d_name);
                                else if(direntp[n]->d_type == DT_REG)
                                printf("%s  ",direntp[n]->d_name);
                                else
                                printf(RED"%s  "END_OF_COLOR,direntp[n]->d_name);
				
			}//end of if (i == 1)
			if(i == 3)  //ls local dir with option -F
			{
			  if(direntp[n]->d_name[0] == '.')
                                        j++;
                                else
                                {

				  if(direntp[n]->d_type == DT_DIR)//type dir and then add "/" at the end of the file name
                                printf(BLUE"%s"END_OF_COLOR"/  ",direntp[n]->d_name);
                                else if(direntp[n]->d_type == DT_REG)
                                printf("%s  ",direntp[n]->d_name);
                                else
                                printf(RED"%s  "END_OF_COLOR,direntp[n]->d_name);
				}
			} //end of if (i ==3)
                   free(direntp[n]);
               		} //end of while
               free(direntp);
		} //end of else
}

		
