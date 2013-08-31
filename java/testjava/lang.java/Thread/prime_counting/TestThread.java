import java.io.*;
public class TestThread
{
	public static void main(String args[])
	{
		if(args.length<1)
		{//要求用户输入一个命令行，否则程序无法进行
			System.out.println("please input a command line parameter");
			System.exit(0);
		}//end of if
	//end of main
	//创建用户Thread子类的对象实例，使其处于NewBorn状态
	PrimeThread subThread=new PrimeThread(Integer.parseInt(args[0]));//parseInt的用处？
	subThread.start();
	while(subThread.isAlive()&&subThread.readyToGoOn()){//readyToGoOn??
		System.out.println("counting the prime number..\n");
		try{
			Thread.sleep(500);
		}
		catch(InterruptedException e)
		{
			return;//return ??
		}
	}// end of while
	
	System.out.println("press a random button to continue...");
	try{
		System.in.read();
	}
	catch(IOException e){};//{}里可加什么语句？？
	}//end of main
}//end of TestThread
