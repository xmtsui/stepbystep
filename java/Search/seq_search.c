#include "stdio.h"  
#include "time.h"
#define MAXSIZE 500000000 /* 存储空间初始分配量 */
int a[MAXSIZE];
/* 无哨兵顺序查找，a为数组，n为要查找的数组个数，key为要查找的关键字 */
long Sequential_Search(int *a,long n,int key)
{
	long i;
	for(i=1;i<=n;i++)
	{
		if (a[i]==key)
			return i;
	}
	return 0;
}
/* 有哨兵顺序查找 */
long Sequential_Search2(int *a,long n,int key)
{
	long i;
	a[0]=key;
	i=n;
	while(a[i]!=key)
	{
		i--;
	}
	return i;
}
/* 有哨兵顺序查找 */
long Sequential_Search3(int *a,long n,int key)
{
	long i;
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
	printf("yes0, %ld\n", 2147483647l);
	// char a[7483647];
	long i,result;
	printf("%s\n","初始化开始");
	for(i=0l;i<=MAXSIZE;i++)
	{
		a[i]=i;
	}
	printf("%s\n","初始化完成");
	printf("%s\n","search1开始");
	time_t ts1,te1;
 	ts1=time(NULL);
 	result=Sequential_Search(a,MAXSIZE,777);
	printf("Sequential_Search:%ld: ",result);
 	te1=time(NULL);
	printf("%s","search1完成，总时间为：");
 	printf("%.20f\n",difftime(te1,ts1));

	printf("%s\n","search2开始");
 	time_t ts2,te2;
 	ts2=time(NULL);
 	result=Sequential_Search2(a,MAXSIZE,777);
	printf("Sequential_Search2:%ld: ",result);
 	te2=time(NULL);
	printf("%s","search2完成，总时间为：");
 	printf("%.20f\n",difftime(te2,ts2));

 	printf("%s\n","search3开始");
 	time_t ts3,te3;
 	ts3=time(NULL);
 	result=Sequential_Search3(a,MAXSIZE,777);
	printf("Sequential_Search3:%ld: ",result);
 	te3=time(NULL);
	printf("%s","search3完成，总时间为：");
 	printf("%.20f\n",difftime(te3,ts3));
	return 0;
}
