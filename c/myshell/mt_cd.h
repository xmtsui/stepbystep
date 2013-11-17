#ifndef _MT_CD_H
#define _MT_CD_H
#endif

#include <unistd.h>

void mt_cd(char *dirname,char *buf_cnv[])
{
	int i = 0;
	if(buf_cnv[i+1] == NULL)
	{
		chdir(USERHOME);
		getcwd(dirname,MAX_SIZE);
	}
	else if(chdir(buf_cnv[i+1]) == -1 )
	{
		printf(RED"WRONG PATH\n"END_OF_COLOR);
	}
	getcwd(dirname,MAX_SIZE);	
}
