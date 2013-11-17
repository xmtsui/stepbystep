#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <string.h>
#include <wait.h>
#include <sys/stat.h>
#include <dirent.h>
#include <time.h>
#include <errno.h>

typedef struct command_struct{
  int comargc;  
  char* command;
  char* comargv[10];
}command_struct;

int com_inside(char*);
int search(char*,char*,char*);

int main(int argc,char** argv)
{
  int flag;
  int i,c;
  int m,n;
  int * status;
  char cwd[255];
  int exeflag;
  char tempcom[10];
  char full_pathname[64]="/home/yg/c_test/0921";
  char pathname[64];
  char scanargv[10][10]={};
  command_struct com;
  while(1)
    {
      if((int)getcwd(cwd,64)==-1)
 printf("get cwd error");
      printf("%s>",cwd);
      /*begin init command_struct*/
      for(m=0;m<10;m++)
 {
   for(n=0;n<10;n++)
     {
       scanargv[m][n]=0;
     }
 }
      i=0;
      while((c=getchar())!='\n')
 {
   if(c!=' ')
     {
       scanargv[i][0]=c;
       scanf("%s",scanargv[i++]+1);
     }
   else
     {
       scanf("%s",scanargv[i++]);
     }
 }
      com.comargc=i-1;
      com.command=scanargv[0];
      i=0;
      while(i<10)
 {   
   com.comargv[i]=scanargv[i];
   i++;
 }   
      /*end init command_struct*/      
      if(com.command=="\n")continue;      
      if((strcmp(com.command,"quit")==0)||(strcmp(com.command,"exit")==0))exit(1);      
      if((flag=com_inside(com.command))==-1)exit(1);
      else if(flag==1)continue;
      else if(flag==0)
 {
   strcpy(tempcom,com.command);   
   if((exeflag=search(full_pathname,tempcom,pathname))==0)
     printf("Bad command or file name\n");
   else 
     {       
       if(fork()==0)
      {
    i=0;
    while(i<=com.comargc)
      {
        argv[i]=com.comargv[i];        
        i++;
      }
    if(execve(pathname,argv,0)==-1)
      {
        printf("error is %s\n",strerror(errno));
      }
      }
       else
      {
    wait(status);
      }
     }
 }
    }
  return 1;
}

 

int com_inside(char* command)
{
  if(command==NULL)return -1;
  if(!(strcmp(command,"ok")))
    {
      printf("OK\n");
      return 1;
    }
  if(!(strcmp(command,"ver")))
    {
      printf("0.0.0.1\n");
      return 1;
    }
  return 0;
}

 

int search(char* path,char* name,char* full_pathname)
{
  int flag;
  int i;
  char pathname[64]={};
  DIR * dirp;
  struct dirent * direntp;
  strcpy(pathname,path);
  i=0;
  while(pathname[i]!=0)
    {
      i++;
    }
  if(pathname[i-1]!=47)
    {
      strcat(pathname,"/");
    }
  if((dirp=opendir(path)))
    {
      while((direntp=readdir(dirp))!=NULL)
 {
   if(strcmp(direntp->d_name,name)==0)
     {
       flag=1;
       break;
     }
   else flag=0;
 }
      closedir(dirp);      
      if(flag)
 {
   strcat(pathname,name);
   strcpy(full_pathname,pathname);  
   return 1;
 }
      else return 0;
    }
  else
    {
      return 0;     
    }  
}

 
