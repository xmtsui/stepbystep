import static java.lang.System.*;
class test_if{
	public static void main(String[] args)
	{
		if(2>0)
			out.printf("hello!\n");
		out.printf("%d\n", 2&3);
		if(2&3>0)//注意优先级
		if((2&3)>0)
			out.printf("heelo1!\n");
	}
}