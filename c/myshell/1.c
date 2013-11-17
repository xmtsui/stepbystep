
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <ctype.h>

#define MAX_SIZE 100
#define pid_t int
#define USERHOME "/home/mariotsui/src/c"
#define ROOTHOME "/home/mariotsui"

/* COLOR DEFINE */
#define RED "\033[0;32;31m"
#define BLUE "\033[0;32;34m"
#define END_OF_COLOR "\033[0m"


#include "mt_login.h"
#include "mt_str_cut.h"
#include "mt_date.h"    //date()
#include "mt_pwd.h"
#include "mt_ls.h"      // -a | -F |  <dirname>
#include "mt_cd.h"
#include "mt_mkdir.h"
#include "mt_rm.h"      //-r
#include "mt_rmdir.h"
#include "mt_vi.h"
#include "mt_man.h"

/*define global variable*/
char  buf_ini[50];
char  *buf_cnv[10];
char  prsnt_dirname[MAX_SIZE];
char  tmp_dirname[MAX_SIZE];
int   user_type;

void login();		//login 
void init();		//Initialize the terminal  
void mt_clear();	//clear the terminal
void judge_utype(); //judge the user name type

main()
{
	char buf[20];
	mt_clear();
	login();
	init();
	fgets(buf,sizeof(buf_ini),stdin); //clear echon buffer
	while(1) 
  {
	getcwd(prsnt_dirname,MAX_SIZE);

	//to decide prompt
	if(user_type == 0)
	printf("mt@linux:%s#",prsnt_dirname);
	else
	printf("mt@linux:%s$",prsnt_dirname);

	fgets(buf_ini,sizeof(buf_ini),stdin);
	buf_ini[strlen(buf_ini)-1]='\0';
	str_cut(buf_ini,buf_cnv);
	if(strncmp(buf_ini,"exit",4)==0)
	{
		break;   
	}  
	else if(strncmp(buf_ini,"clear",5)==0)
	{
		mt_clear();
	}
	else if(strncmp(buf_ini,"pwd",3)==0)
	{
		mt_pwd(prsnt_dirname);
	}
	else if(strncmp(buf_ini,"date",4)==0)
	{
	        mt_date();
	} 
	else if(strncmp(buf_ini,"ls",2)==0)
	{
		mt_ls(prsnt_dirname,buf_cnv);
	}
	else if(strncmp(buf_ini,"cd",2)==0)
	{
		mt_cd(prsnt_dirname,buf_cnv);
	}
	else if(strncmp(buf_ini,"mkdir",5) == 0)
	{
		mt_mkdir(prsnt_dirname,buf_cnv);
	}
	else if(strncmp(buf_ini,"rmdir",5) == 0)
	{
		mt_rmdir(prsnt_dirname,buf_cnv);
	}
	else if(strncmp(buf_ini,"rm",2) == 0)
	{	
		mt_rm(prsnt_dirname,buf_cnv);
	}
	else if(strncmp(buf_ini,"vi",2) == 0)  //this function uses system call
	{	
		mt_vi(buf_cnv);
	}
	else if(strncmp(buf_ini,"man",3) == 0) //manual page
	{
		mt_man();
	}
	else
	{
		printf("Command Not Found\n");
	}

	return(0);
}
void login()
{
	user_type = mt_login();
        judge_utype();
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
void judge_utype()
{
	if(user_type == 0)
	chdir(ROOTHOME);
	else if(user_type == 1)
	chdir(USERHOME);
	else 
	{ 
	printf("Please re-run the program\n");
	exit(1);
	}
}
