#ifndef _MT_LOGIN_H
#define _MT_LOGIN_H
#endif

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <termios.h>
#include <string.h>

#include "/usr/include/mysql/mysql.h"

void echoff(int f);
void echon(int f);
int ini_db(char *u_name,char *u_passwd);

int mt_login()
{
	int u_type = 0;
	char u_name[10];
	char u_passwd[6];
	int f ; //file description
	f = 0 ; //read from stdin
	printf("User Name:");
	scanf("%s",u_name);
	echoff(f);
	printf("Password:");
	scanf("%s",u_passwd);
	echon(f);
	u_type = ini_db(u_name,u_passwd);
	return u_type;
}
void echoff(int f)
{
	struct termios termios_p;
	tcgetattr(f,&termios_p);     //get settings 
	termios_p.c_lflag &= ~ECHO;  //turn off echo
	tcsetattr(f,TCSAFLUSH,&termios_p);  //set setting 
}
void echon(int f)
{
	struct termios termios_p;
	tcgetattr(f,&termios_p);
	termios_p.c_lflag |= ECHO;
	tcsetattr(f,TCSAFLUSH,&termios_p);
}
int ini_db(char *u_name,char *u_passwd)
{
	MYSQL *conn;
	MYSQL_RES *res_ptr;
	MYSQL_ROW row;
	MYSQL_FIELD *field;

	int res;  //mysql_query() success  or fail

	char *server = "localhost";
	char *user = "root";
	char *password = "123";
	char *database = "mt_sh_user";

	conn = mysql_init(NULL);

	//connect to database
	if(!mysql_real_connect(conn,server,user,password,database,0,NULL,0))
	{
		fprintf(stderr,"%s\n",mysql_error(conn));
		mysql_close(conn);
		exit(1);
	}

	//send SQL query
	res = mysql_query(conn,"select name,passwd,type from userinf");
	
	//retrive the record 	
	if(res)
	{
		printf("SELECT error:%s\n",mysql_error(conn));
	}
	else
	{
		res_ptr = mysql_store_result(conn);
		if(res_ptr)
		{
			int tmp = (unsigned long)mysql_num_rows(res_ptr);
			int i = 0,j = 0;
			while((row = mysql_fetch_row(res_ptr)))  //get num of row
			{
				if(!strcmp(u_name,*row))
				{	
					i++;
					if(!strcmp(u_passwd,*(row+1)))
					{
						j++;
						printf("logon success!\n");
						if(!strcmp("1",*(row+2)))
						return 1;
						if(!strcmp("0",*(row+2))) 
						return 0;
					}
					//else 
					//printf("Wrong password,logon failed!\n");
				}
				//else
				//printf("User name not exist,logon failed!\n");
			} //end of while
				//error warning
				if(i == 0)
				printf("\nInvalid User Name\n");
				else if(i == 1 && j == 0)
				printf("\nInvalid Password\n");
			
			if(mysql_errno(conn))
			{
				fprintf(stderr,"Retrive error:%s\n",mysql_error(conn));
			}
		}//end of if(res_ptr)
		mysql_free_result(res_ptr);
	}			
	mysql_close(conn);
}	
