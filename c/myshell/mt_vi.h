#ifndef _MT_VI_H
#define _MT_VI_H
#endif

#include <sys/types.h>
#include <sys/wait.h>

pid_t pid;

void mt_vi(char *buf_cnv[])
{
	int i;
	if((pid=fork())==0)
        {
                execvp(buf_cnv[0],buf_cnv);
                printf("command not found!\n");
                return;
        }
        else
        {
                waitpid(pid,NULL,0);
        }
}




