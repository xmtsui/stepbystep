class Singleton{
	private static volatile Singleton instance = null;
	private static final Object lock = new Object();
	private Singleton(){}

	public static Singleton getInstance1()
	{
		if(instance == null)
			instance = new Singleton();
		return instance;
	}


	public synchronized static Singleton getInstance2()
	{
		if(instance == null)
			instance = new Singleton();
		return instance;
	}

	public static Singleton getInstance3()
	{
		if(instance == null)
		{
			synchronized(lock)
			{
				if(instance == null)
					instance = new Singleton();
			}
		}
		return instance;
	}

	private static final int THREADS_NUM = 20;
	public static void main(String[] args)
	{
		Thread[] threads = new Thread[THREADS_NUM];
		for(int i=0; i<THREADS_NUM; ++i)
		{
			threads[i] = new Thread(){
				@Override
				public void run()
				{
					long start1 = System.currentTimeMillis();
					for(int i=0; i<10000; ++i)
					{
						Singleton.getInstance1();
						assert Singleton.getInstance1() == Singleton.getInstance1();
					}
					long end1 = System.currentTimeMillis();
					long time1 = end1 - start1;

					long start2 = System.currentTimeMillis();
					for(int i=0; i<10000; ++i)
					{
						Singleton.getInstance2();
						assert Singleton.getInstance2() == Singleton.getInstance2();
					}
					long end2 = System.currentTimeMillis();
					long time2 = end2 - start2;

					long start3 = System.currentTimeMillis();
					for(int i=0; i<10000; ++i)
					{
						Singleton.getInstance3();
						assert Singleton.getInstance3() == Singleton.getInstance3();
					}
					long end3 = System.currentTimeMillis();
					long time3 = end3 - start3;
					System.out.println("For big data, time1: " + time1 + "\t| time2: "+ time2 + "\t| time3: "+ time3);
				}
			};
			threads[i].start();
		}
		while(Thread.activeCount()>20)
			Thread.yield();
		System.out.println(Singleton.getInstance1());
		System.out.println(Singleton.getInstance2());
	}
}