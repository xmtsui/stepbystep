/**
 * 测试线程的基本操作
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Random;
class TestThread extends Thread{
	private static int i=0;
	public void run()
	{
		System.out.println("test thread " + i++);
		Random r = new Random();
		try{
			long time = new Long(r.nextInt(10)*500);
			Thread.sleep(time);
			// Thread.currentThread().yield();
			System.out.println(Thread.currentThread());
			// System.out.println(Thread.currentThread().interrupted());
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		TestThread[] tt = new TestThread[10];
		Random r = new Random();
		for(TestThread item : tt)
		{
			item = new TestThread();
			item.setPriority(r.nextInt(10)+1);//[1,10)
			item.start();
			try{

				System.out.println("---1----: "+item.getState());
				item.join(100);
				System.out.println("---2----: "+item.getState());
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}