#include <string.h>
#include <stdlib.h>
#include <conio.h>
#include <stdio.h>
#include <dir.h>
#include <dos.h>
#include <time.h>

/*定义全局变量*/
char root_dir[3];
char pre_dir[255];
char *cmd_line[255];
char curuser[10];

struct userinf
{
    char username[10];
    char userpass[10];
};

/*函数申明*/
void init();
int login();
int getcmd();
void dir();
void cd();
void clear();
void newdir();
void deldir();
void del();
void copy();
void cut();
void account();
void help();

main()
{
    init();
    while(1)/* 消息循环 */
    {
        switch(getcmd())
        {
            case 0:
                help();
                break;
            case 1:
                dir();
                break;
            case 2:
                cd();
                break;
            case 3:
                newdir();
                break;
            case 4:
                deldir();
                break;
            case 5:
                del();
                break;
            case 6:
                copy();
                break;
            case 7:
                cut();
                break;
            case 8:
                account();
                break;
        }
    }
}

void init()/* 程序初始化 */
{
    if(login()==0)
    {
        exit(0);
    }
    strcpy(pre_dir,"C:\\");/*设定当前目录*/
    clear();/* 清屏 */
    printf("S Shell-Above Windows XP [Ver 1.0]\n");
    printf("(C) Copyright 2007 stars_625.\n\n");
    getchar();/* 清空缓冲区 */
}

int login()/* 程序登陆 */
{
    char name[10];
    char pass[10];
    int logintime=3;
    FILE *fp;
    struct userinf inf;
    while(logintime>0)/* 登陆错误超过三次自动退出 */
    {
        printf("Login:");
        scanf("%s",name);
        printf("Password:");
        scanf("%s",pass);
        if((fp=fopen("inf.dll","r"))==NULL)
        {
            printf("Can't open inf.dll file!\n");
            printf("Press any key to exit...");
            getch();
            exit(0);
        }
        while(fread(&inf,sizeof(inf),1,fp)==1 && strcmp(inf.username,name)!=0)
        {
        }
        fclose(fp);
        if(strcmp(inf.username,name)==0)
        {
            if(strcmp(inf.userpass,pass)==0)
            {
                strcpy(curuser,inf.username);
                clear();
                return 1;
            }
            else
            {
                printf("Login error, Press any key to relogin!\n");
                getch();
                clear();
            }
        }
        else
        {
            printf("The user is not exist, Press any key to relogin!\n");
            getch();
            clear();
        }
        logintime--;
    }
    printf("Login error above three times, Press any key to exit!");
    getch();
    return 0;
}

int getcmd()/* 获得命令 */
{
    int i=0,j=0,k=0;
    char buf[255];
    printf("%s>",pre_dir);/* 打印提示符 */
    fgets(buf, 255, stdin);
    cmd_line[j] = calloc(255, sizeof(char));
    while (buf[i] != '\n' && buf[i] != '\0')/* 命令分析 */
    { 
        if (buf[i] != ' ')
        { 
            cmd_line[j][k] = buf[i];
            ++k; 
        } 
        else         
        { 
            cmd_line[j + 1] = calloc(255, sizeof(char));
            k = 0; 
            ++j; 
        }
        ++i; 
    }
    cmd_line[j + 1]=0;
    if(strcmp(cmd_line[0],"exit")==0)
    {
        exit(0);
    }
    else if(strcmp(cmd_line[0],"/?")==0 || strcmp(cmd_line[1],"/?")==0)
    {
        return 0;
    }
    else if(strcmp(cmd_line[0],"dir")==0)
    {
        return 1;
    }
    else if(strcmp(cmd_line[0],"cd")==0)
    {
        return 2;
    }
    else if(strcmp(cmd_line[0],"newdir")==0)
    {
        return 3;
    }
    else if(strcmp(cmd_line[0],"deldir")==0)
    {
        return 4;
    }
    else if(strcmp(cmd_line[0],"del")==0)
    {
        return 5;
    }
    else if(strcmp(cmd_line[0],"copy")==0)
    {
        return 6;
    }
    else if(strcmp(cmd_line[0],"cut")==0)
    {
        return 7;
    }
    else if(strcmp(cmd_line[0],"account")==0)
    {
        return 8;
    }
    else if(cmd_line[0][1]==':')
    {
        strcpy(pre_dir,cmd_line[0]);
        strcat(pre_dir,"\\");
    }
    else if(strcmp(cmd_line[0],"clear")==0)
    {
        clear();
    }
    else
    {
        printf("The command is not supported!\n");
    }
}

void dir()/* 列出文件及文件夹 */
{
    struct ffblk ff;
    char filepath[255];
    strcpy(filepath,pre_dir);
    findfirst(strcat(filepath,"*.*"),&ff,FA_DIREC);
    if(ff.ff_attrib==16)
    {
        printf("<DIR>\t");
    }
    else
    {
        printf("    \t");
    }
    printf("%s\n",ff.ff_name);
    while(findnext(&ff)==0)
    {
        if(ff.ff_attrib==16)
        {
            printf("<DIR>\t");
        }
        else
        {
            printf("    \t");
        }
        printf("%s\n",ff.ff_name);
    }
}

void cd()/* 改变当前目录 */
{
    int i=0;
    struct ffblk ff;
    char filepath[255];
    strcpy(filepath,pre_dir);
    if(strcmp(cmd_line[1],"..")==0)/* 返回上一层目录 */
    {
        while(filepath[i]!='\0')
        {
            i++;
        }
        if(filepath[i-2]!=':')
        {
            i=i-2;
            while(filepath[i]!='\\' && i>=2)
            {
                i--;
            }
            filepath[i+1]='\0';
            strcpy(pre_dir,filepath);
        }
    }
    else if(strcmp(cmd_line[1],"\\")==0)/*返回根目录*/
    {
        while(filepath[i]!='\\')
        {
            i++;
        }
        filepath[i+1]='\0';
        strcpy(pre_dir,filepath);
    }
    else
    {
        findfirst(strcat(filepath,"*.*"),&ff,FA_DIREC);
        while(strcmp(ff.ff_name,cmd_line[1])!=0)
        {
            if(findnext(&ff)!=0)
            {
                break;
            }
        }
        if(strcmp(ff.ff_name,cmd_line[1])==0)
        {
            strcat(pre_dir,cmd_line[1]);
            strcat(pre_dir,"\\");
        }
        else
        {
            printf("Can't find the file!\n");
        }
    }
}

void clear()
{
    clrscr();
}

void newdir()
{
    char filepath[255];
    strcpy(filepath,pre_dir);
    if(mkdir(strcat(filepath,cmd_line[1]))==0)
    {
        printf("Make dir '%s' successfully!\n",cmd_line[1]);
    }
    else
    {
        printf("Make dir error!\n");
    }
}

void deldir()
{
    char filepath[255];
    strcpy(filepath,pre_dir);
    if(rmdir(strcat(filepath,cmd_line[1]))==0)
    {
        printf("Delete dir '%s' successfully!\n",cmd_line[1]);
    }
    else
    {
        printf("Delete dir error!\n");
    }
}

void del()
{
    char filepath[255];
    strcpy(filepath,pre_dir);
    if(unlink(strcat(filepath,cmd_line[1]))==0)
    {
        printf("Delete %s successfully!\n",cmd_line[1]);
    }
    else
    {
        printf("Delete error!\n");
    }
}

void copy()
{
    char filepath[255];
    char sourcepath[255];
    char aimpath[255];
    FILE *newfp;
    FILE *oldfp;
    char ch;
    strcpy(filepath,pre_dir);
    if(cmd_line[1][1]!=':')
    {
        strcpy(sourcepath,filepath);
        strcat(sourcepath,cmd_line[1]);
    }
    else
    {
        strcpy(sourcepath,cmd_line[1]);
    }
    if(cmd_line[2][1]!=':')
    {
        strcpy(aimpath,filepath);
        strcat(aimpath,cmd_line[2]);
    }
    else
    {
        strcpy(aimpath,cmd_line[2]);
    }
    if((oldfp=fopen(sourcepath,"r"))==NULL)
    {
        printf("Can't open old file!\n");
    }
    if((newfp=fopen(aimpath,"w"))==NULL)
    {
        printf("Can't creat new file!\n");
    }
    while((ch=fgetc(oldfp))!=EOF)
    {
        fputc(ch,newfp);
    }
    fclose(oldfp);
    fclose(newfp);
    printf("Copy from %s to %s successfully!\n",sourcepath,aimpath);
}

void cut()
{
    char filepath[255];
    char sourcepath[255];
    char aimpath[255];
    FILE *newfp;
    FILE *oldfp;
    char ch;
    strcpy(filepath,pre_dir);
    if(cmd_line[1][1]!=':')
    {
        strcpy(sourcepath,filepath);
        strcat(sourcepath,cmd_line[1]);
    }
    else
    {
        strcpy(sourcepath,cmd_line[1]);
    }
    if(cmd_line[2][1]!=':')
    {
        strcpy(aimpath,filepath);
        strcat(aimpath,cmd_line[2]);
    }
    else
    {
        strcpy(aimpath,cmd_line[2]);
    }
    if((oldfp=fopen(sourcepath,"r"))==NULL)
    {
        printf("Can't open old file!\n");
    }
    if((newfp=fopen(aimpath,"w"))==NULL)
    {
        printf("Can't creat new file!\n");
    }
    while((ch=fgetc(oldfp))!=EOF)
    {
        fputc(ch,newfp);
    }
    fclose(oldfp);
    fclose(newfp);
    if(unlink(sourcepath)==0)
    {
        printf("Cut from %s to %s successfully!\n",sourcepath,aimpath);
    }
    else
    {
        printf("Delete old file error!\n");
    }

}

void account()
{
    FILE *fp;
    struct userinf inf;
    if(strcmp(cmd_line[1],"/add")==0)
    {
        if((fp=fopen("inf.dll","r+"))==NULL)
        {
            printf("Can't open inf.dll file!\n");
            printf("Press any key to exit...");
            getch();
        }
        while(fread(&inf,sizeof(inf),1,fp)==1 && strcmp(inf.username,cmd_line[2])!=0)
        {
        }
        if(strcmp(inf.username,cmd_line[2])==0)
        {
            printf("Create user error, the user is exist!\n");
        }
        else
        {
            strcpy(inf.username,cmd_line[2]);
            strcpy(inf.userpass,cmd_line[3]);
            if(fwrite(&inf,sizeof(inf),1,fp)==1)
            {
                printf("Create user %s successfully!\n",inf.username);
            }
            else
            {
                printf("Create user error!\n");
            }
        }
        fclose(fp);
    }
    else if(strcmp(cmd_line[1],"/edit")==0)
    {
        if((fp=fopen("inf.dll","r+"))==NULL)
        {
            printf("Can't open inf.dll file!\n");
            printf("Press any key to exit...");
            getch();
        }
        while(fread(&inf,sizeof(inf),1,fp)==1 && strcmp(inf.username,cmd_line[2])!=0)
        {
        }
        if(strcmp(inf.username,cmd_line[2])!=0)
        {
            printf("Edit user error, the user is not exist!\n");
        }
        else
        {
            strcpy(inf.username,cmd_line[2]);
            strcpy(inf.userpass,cmd_line[3]);
            fseek(fp,-20L,1);
            if(fwrite(&inf,sizeof(inf),1,fp)==1)
            {
                printf("Edit user %s successfully!\n",inf.username);
            }
            else
            {
                printf("Edit user error!\n");
            }
        }
        fclose(fp);
    }
    else
    {
        printf("Please enter correct parameter,type /? for help!\n");
    }
}

void help()
{
    if(strcmp(cmd_line[0],"/?")==0)
    {
        printf("The list of commands.\n\n");
        printf("  dir\t\tList the files and dirs.\n");
        printf("  cd\t\tChange the dir.\n");
        printf("  clear\t\tClear the screen.\n");
        printf("  newdir\tMake a dir.\n");
        printf("  deldir\tDelete a dir.\n");
        printf("  del\t\tDelete a file.\n");
        printf("  copy\t\tCopy a file from a place to another.\n");
        printf("  cut\t\tCut a file from a place to another.\n");
        printf("  account\tAdd edit or delete a account.\n\n");
        printf("For more information add type /? after command.\n\n");
        printf("Notice:All the command line is capitalization distinction!\n\n");
    }
    else
    {
        if(strcmp(cmd_line[0],"dir")==0)
        {
            printf("List the files and dirs.\n\n");
            printf("dir path\n\n");
            printf("  path\t\tThe dir you want to list.\n");
            printf("  \t\tif path is NULL then list the current dir.\n\n");
        }
        else if(strcmp(cmd_line[0],"cd")==0)
        {
            printf("Change the dir.\n\n");
            printf("cd < \\ | .. | path>\n\n");
            printf("  \\\t\tReturn to the root dir.\n");
            printf("  ..\t\tReturn to the parent dir.\n");
            printf("  path\t\tThe dir you want change to.\n\n");
        }
        else if(strcmp(cmd_line[0],"clear")==0)
        {
            printf("Clear the screen.\n\n");
            printf("clear\n\n");
        }
        else if(strcmp(cmd_line[0],"newdir")==0)
        {
            printf("Make a dir.\n\n");
            printf("newdir path\n\n");
            printf("  path\t\tThe dir path which you want to create.\n\n");
        }
        else if(strcmp(cmd_line[0],"deldir")==0)
        {
            printf("Delete a dir.\n\n");
            printf("deldir path\n\n");
            printf("  path\t\tThe dir path which you want to delete.\n\n");
        }
        else if(strcmp(cmd_line[0],"del")==0)
        {
            printf("Delete a file.\n\n");
            printf("del filepath\n\n");
            printf("  filepath\tThe filepath which you want to delete.\n\n");
        }
        else if(strcmp(cmd_line[0],"copy")==0)
        {
            printf("Copy a file from a place to another.\n\n");
            printf("copy source aim\n\n");
            printf("  source\tThe source file path.\n");
            printf("  aim\t\tThe aim file path.\n\n");
        }
        else if(strcmp(cmd_line[0],"cut")==0)
        {
            printf("Cut a file from a place to another.\n\n");
            printf("cut source aim\n\n");
            printf("  source\tThe source file path.\n");
            printf("  aim\t\tThe aim file path.\n\n");
        }
        else if(strcmp(cmd_line[0],"account")==0)
        {
            printf("Add edit or delete a account.\n\n");
            printf("account < /add | /edit | /del > username userpass\n\n");
            printf("  username\tThe account name.\n");
            printf("  userpass\tThe account password.\n");
            printf("  /add\t\tAdd a account.\n");
            printf("  /edit\t\tEdit a account.\n");
            printf("  /del\t\tDelete a account.\n\n");
        }
        else
        {
            printf("The command is not supported!\n");
        }
    }
}
