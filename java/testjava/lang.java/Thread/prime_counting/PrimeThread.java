import java.io.*;
public class PrimeThread extends Thread{
	//创建用户自己的Thread子类，在其run（）中实现程序子线程操作
	boolean m_bContinue=true;//标志本线程是否继续
	int m_nCircleNum;//循环的上限
	PrimeThread(int Num)
	{
		m_nCircleNum=Num;
	}
	boolean readyToGoOn()//判断本线程是否继续
	{
		return(m_bContinue);
	}
	public void run()
	{//继承并重载父类Thread的run（）方法，在该线程被启动时自动执行
		int number=3;
		boolean flag=true;
		while(true)//无限循环
		{
			for(int i=2;i<number;i++)//检查number是否为素数
			if(number%i==0)
				flag=false;
			if(flag)
				System.out.println(number+"是素数");
			else
				System.out.println(number+"不是素数");
			
				number++;//修改number的值，为下一轮循环做准备
				if(number>m_nCircleNum)//到达要求检查数制的上限
				{
					m_bContinue=false;//结束此线程
					return;//结束此run（）方法，结束线程
				}
			flag=true;
			try{
				sleep(500);
			}
			catch(InterruptedException e)
			{
				return;
			}
			
		}//end of while
	}//end of run()
}//end of PrimeThread
