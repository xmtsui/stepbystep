import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class RandomChars{
	private char[] c={'a','b','c','d','e','f'};
	private Lock lock = new ReentrantLock();
	private int len;
	public RandomChars()
	{
		len = c.length;
	}

	public int getLen()
	{
		return len;
	}
	
	public void print(int i)
	{
		try{
			Thread.sleep(100);
			System.out.println("Curr:" + Thread.currentThread());
			lock.lock();
			System.out.print(c[i]);
			// lock.unlock();
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	public static void main(String... args)
	{
		RandomChars rand = new RandomChars();
		int len = rand.getLen();
		for(int i=0; i<len; ++i)
		{
			Thread th = new Thread(new CharPrintThread(rand, i));
			th.start();

			// try{
			// 	th.join();
			// }catch(InterruptedException e)
			// {
			// 	e.printStackTrace();
			// }
		}

		System.out.println();
	}
	private static class CharPrintThread implements Runnable{
		private static RandomChars rand;
		private static int i = 0;
		public CharPrintThread(RandomChars rand, int i)
		{
			this.rand = rand;
			this.i = i;
		}
		@Override
		public void run()
		{
			rand.print(i);
		}
	}
}