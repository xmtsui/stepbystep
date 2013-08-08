#include "stdio.h"  
#include "stdlib.h"
#include "time.h"
// #define MAXSIZE 1024*1024 /* 存储空间初始分配量 */
// int a[MAXSIZE];
/* 无哨兵顺序查找，a为数组，n为要查找的数组个数，key为要查找的关键字 */
int Sequential_Search(int *a,int n,int key)
{
	int i;
	for(i=1;i<=n;i++)
	{
		if (a[i]==key)
			return i;
	}
	return 0;
}
/* 有哨兵顺序查找，从大到小 */
int Sequential_Search2(int *a,int n,int key)
{
	int i;
	a[0]=key;
	i=n;
	while(a[i]!=key)
	{
		i--;
	}
	return i;
}
/* 有哨兵顺序查找,从小到大 */
int Sequential_Search3(int *a,int n,int key)
{
	int i;
	a[n-1]=key;
	i=0;
	while(a[i]!=key)
	{
		i++;
	}
	return i;
}

int main(void)
{    
	int mb = 1024 * 1024;
	int gb = 1023 * 1024 * 1024;
	//栈上分配
	// int MAXSIZE = mb*2-1580;
	// int a[MAXSIZE];
	
	//堆上分配(2g-3g)
	int MAXSIZE = mb*100;
	int *a = (int *)malloc(sizeof(int)*MAXSIZE);
	if(a!=NULL)
		printf("malloc ok\n");
	int i,result;
	printf("%s\n","初始化开始");
	for(i=0;i<MAXSIZE;i++)
	{
		a[i]=i;
	}
	printf("%s\n","初始化完成");
	
	printf("%s\n","search1开始");
	time_t ts1,te1;
 	ts1=time(NULL);
 	result=Sequential_Search(a,MAXSIZE,MAXSIZE-2);
	printf("Sequential_Search:%d: ",result);
 	te1=time(NULL);
	printf("%s","search1完成，总时间为：");
 	printf("%.20f\n",difftime(te1,ts1));

	printf("%s\n","search2开始");
 	time_t ts2,te2;
 	ts2=time(NULL);
 	// result=Sequential_Search2(a,MAXSIZE,MAXSIZE/2);
	printf("Sequential_Search2:%d: ",result);
 	te2=time(NULL);
	printf("%s","search2完成，总时间为：");
 	printf("%.20f\n",difftime(te2,ts2));

 	printf("%s\n","search3开始");
 	time_t ts3,te3;
 	ts3=time(NULL);
 	result=Sequential_Search3(a,MAXSIZE,MAXSIZE-2);
	printf("Sequential_Search3:%d: ",result);
 	te3=time(NULL);
	printf("%s","search3完成，总时间为：");
 	printf("%.20f\n",difftime(te3,ts3));
 	free(a);
	return 0;
}
