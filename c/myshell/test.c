#include <stdio.h>
#include <string.h>

#include <readline/readline.h>
#include <readline/history.h>
#include "mt_str_cut.h"

void set_prompt(char *tmp);
main()
{
char *line;
char prompt[200];
while(1)
{
	set_prompt(prompt);
	if(!(line = readline(prompt))
	break;
}
}
void set_prompt(char *tmp)
{
	tmp = "$";
}
/*	int i = 0;
	char buf[10];
	char *buf1[10];
	fgets(buf,sizeof(buf),stdin);
	str_cut(buf,buf1);
	while(buf1[i] != NULL)
	{
		printf("%s\n",++buf1[i]);
		//printf("compare -a:%d\n",strcmp("ls\0",buf1[i]));
		i++;
	}
*/
	
