
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h> 
#include <unistd.h>


#include "mt_date.h"    //date()

#define pid_t int 
/*define extern variable*/
char root_dir[3];

void  init();		//Initialize the terminal  
void  mt_clear();	//clear the terminal

main()
{
	char buf[20];
	pid_t pid;
	mt_clear();
	init();
	while(1) 
  {
	printf("mt@linux:/#");
	fgets(buf,sizeof(buf),stdin);
	buf[strlen(buf)-1]='\0';
	if(strncmp(buf,"exit",4)==0)
	{
		break;   
	}  
	else if(strncmp(buf,"clear",5)==0)
	{
		mt_clear();
	}
	else if(strncmp(buf,"pwd",3)==0)
	{
		printf("yes");
	}
	else if(strncmp(buf,"date",4)==0)
	{
	       mt_date();
	} 
	else
	{
		printf("Command Not Found\n");
	}
	/*if((pid=fork())==0)
	{
		execlp(buf,buf,NULL);
		printf("command not found!\n");
		return(1);
	}
	else
	{
		waitpid(pid,NULL,0);
	}*/
  }
	return(0);
}
void init() 
{
	mt_clear();
	printf("MT Shell Above Ubuntu [Ver 8.10]\n");
        printf("(C) Copyright 2009 by MarioTsui\n");
}
void mt_clear() 
{
	printf("%s","\033[1H\033[2J");
}
