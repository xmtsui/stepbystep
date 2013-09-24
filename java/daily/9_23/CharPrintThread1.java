import java.util.concurrent.locks.ReentrantLock;
class CharPrintThread1 extends Thread{
	private char[] a;
	private ReentrantLock rel;
	public CharPrintThread1(ReentrantLock rel, char[] a)
	{
		this.rel = rel;
		this.a = a;
	}

	@Override
	public void run()
	{
		rel.lock();
		doPrint(a);
		rel.unlock();
		/*if(rel.tryLock())
		{
			rel.lock();
			doPrint(a);
		}
		if(rel.isLocked())
		rel.unlock();*/
	}

	public void doPrint(char[] a)
	{
		if(a==null)
			return;
		int len = a.length;
		int rand = (int)(len*Math.random());
		while(a[rand]=='0')
		{
			rand = (int)(len*Math.random());
		}
		System.out.print(a[rand]);
		a[rand]='0';
	}

	public static void main(String[] args)
	{
		char[] a = {'a','b','c','d','e','f','g','h'};
		int MAX_THREAD = a.length;
		ReentrantLock rel = new ReentrantLock();
		for(int i=0; i<MAX_THREAD; ++i)
		{
			CharPrintThread1 cpt = new CharPrintThread1(rel, a);
			cpt.start();
			try{
				Thread.sleep(1);
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println();
	}
}