#ifndef _MT_MAN_H
#define _MT_MAN_H
#endif


void mt_man()
{
	int i = 1; 
	printf("----------------------------------------------------------\n");
	printf("     Commands List       \n");
	printf("%d. pwd    usage: pwd      \n",i++); 
	printf("%d. date   usage: date     \n",i++);
	printf("%d. clear  usage: clear    \n",i++);
 	printf("%d. ls     usage: ls    [-a | -F] [directory]       \n",i++);
	printf("%d. cd     usage: cd    [-a | -F] [directory]       \n",i++);
 	printf("%d. mkdir  usage: mkdir [relative path + new directory name | absolute path +                            new directory name]     \n",i++);
 	printf("%d. rmdir  usage: rmdir [relative path + exist directory name | absolute path +                          exist directory name]     \n",i++);
	printf("%d. vi     usage: vi    [relative path + new file name | absolute path +                                 new file name]     \n",i++);
 	printf("%d. rm     usage: rm    [relative path + exist file name | absolute path +                               exist file name]     \n",i++);
	printf("----------------------------------------------------------\n");

}


	
