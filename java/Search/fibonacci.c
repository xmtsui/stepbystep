#include "stdio.h"  
#include "stdlib.h"
#include "time.h"
// #define MAXSIZE 1024*1024 /* 存储空间初始分配量 */
// int a[MAXSIZE];
// 
int F[100]; /* 斐波那契数列 */
// /* 斐波那契查找 */
int Fibonacci_Search(int *a,int n,int key)
{
	int low,high,mid,i,k=0;
	low=1;	/* 定义最低下标为记录首位 */
	high=n;	/* 定义最高下标为记录末位 */
	while(n>F[k]-1)
		k++;
	for (i=n;i<F[k]-1;i++)
		a[i]=a[n];
	
	while(low<=high)
	{
		mid=low+F[k-1]-1;
		if (key<a[mid])
		{
			high=mid-1;		
			k=k-1;
		}
		else if (key>a[mid])
		{
			low=mid+1;		
			k=k-2;
		}
		else
		{
			if (mid<=n)
				return mid;		/* 若相等则说明mid即为查找到的位置 */
			else 
				return n;
		}
		
	}
	return 0;
}

/* 折半查找 */
int Binary_Search(int *a,int n,int key)
{
	int low,high,mid;
	low=1;	/* 定义最低下标为记录首位 */
	high=n;	/* 定义最高下标为记录末位 */
	while(low<=high)
	{
		mid=(low+high)/2;	/* 折半 */
		if (key<a[mid])		/* 若查找值比中值小 */
			high=mid-1;		/* 最高下标调整到中位下标小一位 */
		else if (key>a[mid])/* 若查找值比中值大 */
			low=mid+1;		/* 最低下标调整到中位下标大一位 */
		else
		{
			return mid;		/* 若相等则说明mid即为查找到的位置 */
		}
		
	}
	return 0;
}

int main(void)
{    
	int kb = 1024;
	int mb = 1024 * 1024;
	int gb = 1023 * 1024 * 1024;
	//栈上分配
	// int MAXSIZE = mb*2-1580;
	// int a[MAXSIZE];
	
	//堆上分配(2g-3g)
	int MAXSIZE = kb*10;
	// int MAXSIZE = kb;
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
	
 	F[0]=0;
	F[1]=1;
	for(i = 2;i < 100;i++)  
	{ 
		F[i] = F[i-1] + F[i-2];  
	} 
	result=Fibonacci_Search(a,MAXSIZE,62);
	printf("Fibonacci_Search:%d \n",result);
	result=Binary_Search(a,MAXSIZE,62);
	printf("Binary_Search:%d \n",result);
 	

 	int index=0;
 	printf("%s\n","fabonacci search开始");
	time_t ts1,te1;
 	ts1=time(NULL);
 	for(index=0; index<1000000; index++){
		Fibonacci_Search(a,MAXSIZE,1);
	}
	te1=time(NULL);
	printf("%s","search1完成，总时间为：");
 	printf("%.20f\n",difftime(te1,ts1));

 	printf("%s\n","bsearch开始");
 	time_t ts2,te2;
 	ts2=time(NULL);
 	for(index=0; index<1000000; index++){
 		Binary_Search(a,MAXSIZE,1);
	}
 	te2=time(NULL);
	printf("%s","search2完成，总时间为：");
 	printf("%.20f\n",difftime(te2,ts2));
 	free(a);
	return 0;
}
