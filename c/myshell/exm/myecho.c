/*用C语言编写myecho程序，模拟系统命令echo功能. 王进瑞*/
#include <getopt.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>


/*打印程序帮助信息*/
void printf_help()
{
	printf("\n\nmyecho 帮助信息:\n\n");
	printf("功能说明：显示文字。\n"
			"语    法：myecho [-ne][字符串]或 myecho [--help][--version] \n"
			"补充说明：myecho会将输入的字符串送往标准输出。输出的字符串间以空白字符隔开, 并在最后加上换行号。\n"
			"参    数：\n"
			"\t  --help  -h  显示帮助\n"
			"\t  --version  -v  显示版本信息\n"
			"\t  -n 不要在最后自动换行\n"
			"\t  -e 若字符串中出现以下字符，则特别加以处理，而不会将它当成一般文字输出\n"
			"\t\t\\a 发出警告声；\n"
			"\t\t\\b 删除前一个字符；\n"
			"\t\t\\c 最后不加上换行符号；\n"
			"\t\t\\f 换行但光标仍旧停留在原来的位置；\n"
			"\t\t\\n 换行且光标移至行首；\n"
			"\t\t\\r 光标移至行首，但不换行；\n"
			"\t\t\\t 插入tab；\n"
			"\t\t\\v 与\\f相同；\n"
			"\t\t\\\\ 插入\\字符；\n"
			"\t\t\\nnn 插入nnn(八进制)所代表的ASCII字符;\n");
	
	exit(0);
	
}



int main(int argc,char* argv[])
{
	//下一个要处理的参数
	int i,j,cout,next_option=0;
	char a[100];
	int b=0;
	int ascii_change;

//	const char* const shor_options = "hv";

	//标识长选项和对应短选项的数组
	const struct option long_options[] = {
		{ "help", 0, NULL, 'h' },
		{ "version", 0, NULL, 'v' },
		{ NULL, 0, NULL, 0 },
		};

	//可执行文件名参数
	const char* f_name = NULL;

	//argv[0]始终指向可执行文件的文件名
	f_name = argv[0];
	int longindex;
	
	while (next_option != -1){
		next_option = getopt_long (argc, argv, "hvne", long_options, NULL);
		switch (next_option){
			// -h or --help
			case 'h':
				printf_help();
				break;
			// -v or --version
			case 'v':
				printf("\nAbout Myecho:A practice thing.\n\n");
				break;
			//出现未定义的选项
			case '?':
				printf("\n错误：出现未定义选项。\n");
				printf_help();
				break;
			//不换行输出
			case 'n':
				for(i=2;i<argc;i++)
				printf("%s ",argv[i]);
				//printf("\n");
				break;
			//特殊字符处理部分
			case 'e': 
				for(j=2;j<argc;j++){
					//判断字符串长度
					if((cout=strlen(argv[j]))<100){
						memcpy(a,argv[j],strlen(argv[j]));
						for(i=0;i<cout;i++){
							//遇到'\\'输出一个'\'
							if((a[i]==92)&&(a[i+1]==92)){
								printf("\\");
								i=i+2;
							}
							//遇到'\a'发出警告声
							if((a[i]==92)&&(a[i+1]==97)){
								printf("\a");
								i=i+2;
							}
							//遇到'\b'删除前一个字符
							if((a[i]==92)&&(a[i+1]==98)){
								printf("\b");
								i=i+2;
							}
							//遇到'\n'换行，光标到行首
							if((a[i]==92)&&(a[i+1]==110)){
								printf("\n");
								i=i+2;
							}
							//遇到'\f'或'\v'换行且光标在原来位置
							if( ((a[i]==92)&&(a[i+1]==102)) || ((a[i]==92)&&(a[i+1]==118)) ){
								printf("\f");
								i=i+2;
							}
							//遇到'\r'光标移到行首，但不换行
							if((a[i]==92)&&(a[i+1]==114)){
								printf("\r");
								i=i+2;
							}
							//遇到'\t'插入一个TAB
							if((a[i]==92)&&(a[i+1]==116)){
								printf("\t");
								i=i+2;
							}
							//遇到'\c'，跳过，后面处理
							if((a[i]==92)&&(a[i+1]==99)){
								i=i+2;
							}
							//遇到'\nnn'输出ASCII码表对应的字符
							if((a[i]==92)&&(a[i+1]>=48)&&(a[i+1]<=50)){
								if((a[i+2]>=48)&&(a[i+2]<=57)&&(a[i+3]>=48)&&(a[i+3]<=57)){
									ascii_change=((int)a[i+1])*100+((int)a[i+2])*10+(int)a[i+3]+48;
									printf("%c",ascii_change);
									i=i+3;
								}
							}
							//否则按顺序输出
							else
							  printf("%c",a[i]);
						}
						//遇到'\c'时
						for(i=0;i<cout;i++){
							if((a[i]==92)&&(a[i+1]==99))
							  b=1;
						}
						
					}else 
					  printf("字符串过长。");
					printf(" ");
				}
				//若有'\c'出现，字符输出完毕后直接跳出，否则打印回车
				if(b==1)
				  break;
				else 
				  printf("\n");
				break;
			//没有命令参数按顺序输出
			default :
				for(i=1;i<argc;i++)
				printf("%s ",argv[i]);
				printf("\n");
				break;

		}
		break;
	}return 0;	
}
