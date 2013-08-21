#include <stdio.h>
int main()
{
	/*1个字节*/
	char 			byte_max1 	= 127; 			 printf("char max\t%d\n", byte_max1);
	char 			byte_min1 	= -128;			 printf("char min\t%d\n", byte_min1);
	char 			byte_max2 	= 127+1; 		 printf("char max+1\t%d\n", byte_max2);
	char 			byte_min2 	= -128-1; 		 printf("char min-1\t%d\n\n", byte_min2);

	/* 2个字节=1024*64 */
	short 			short_max1 	= 32767; 		 printf("short max\t%d\n", short_max1);	
	short 			short_min1 	= -32768; 		 printf("short min\t%d\n", short_min1);	
	short 			short_max2 	= 32767+1;		 printf("short max+1\t%d\n", short_max2);
	short 			short_min2 	= -32768-1;		 printf("short min-1\t%d\n\n", short_min2);	

	/*2个字节*/
	unsigned short 	u_s_max1	= 65535;		 printf("un_sh max\t%d\n", u_s_max1);
	unsigned short 	u_s_min1	= 0;			 printf("un_sh min\t%d\n", u_s_min1);
	unsigned short 	u_s_max2	= 65535+1;		 printf("un_sh max+1\t%d\n", u_s_max2);
	unsigned short 	u_s_min2	= 0-1;			 printf("un_sh min-1\t%d\n\n", u_s_min2);
	
	/* 4个字节=1024*1024*1024*2   */
	/* 1073741824*2 = 2147483648 */
	int 			int_max1	= 2147483647; 	 printf("int max \t%d\n", int_max1);	
	int 			int_min1	= -2147483648; 	 printf("int min \t%d\n", int_min1);	
	int 			int_max2	= 2147483647+1;  printf("int max+1\t%d\n", int_max2);	
	int 			int_min2	= -2147483648-1; printf("int min-1\t%d\n\n", int_min2);	
	
	/* 8个字节=1024*1024*1024*1024*1024*1024 */
	/* 1073741824 * 1073741824 				*/
	/* 1000000000 * 1000000000 				*/
	/* 1000000000000000000 					*/
	/* 9223372036854775807  				*/
	long 			long_max1	= 9223372036854775807; 	 printf("long max\t%ld\n", long_max1);
	long 			long_min1	= -9223372036854775808; 	 printf("long min\t%ld\n", long_min1);
	//long 			long_max2	= 2147483647+1;  printf("long max+1\t%ld\n", long_max2); 
	long 			long_max2	= 9223372036854775807+1;  printf("long max+1\t%ld\n", long_max2); 
	long 			long_min2	= -9223372036854775808-1;  printf("long min-1\t%ld\n", long_min2); 


	printf("%ld\n", sizeof(char));//1
	printf("%ld\n", sizeof(short));//2
	printf("%ld\n", sizeof(int));//4
	printf("%ld\n", sizeof(long));//8
	printf("%ld\n", sizeof(float));//4
	printf("%ld\n", sizeof(double));//8	
	printf("%ld\n", sizeof(char *));//64bit机器是8，32位的是4
}