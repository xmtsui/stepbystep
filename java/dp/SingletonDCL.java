/**
 * Double check locking
 */
class SingletonDCL{
	private volatile static SingletonDCL instance;
	private static final Object lock = new Object();
	private SingletonDCL(){}

	public static SingletonDCL getInstance()
	{
		if(instance == null)
		{
			synchronized(lock)
			{
				if(instance == null)
					instance = new SingletonDCL();
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
					// System.out.println(SingletonDCL.getInstance());
					// System.out.println(SingletonDCL.getInstance());
					assert SingletonDCL.getInstance() == SingletonDCL.getInstance();
				}
			};
			threads[i].start();
		}
		System.out.println(SingletonDCL.getInstance());
		System.out.println(SingletonDCL.getInstance());
	}
}