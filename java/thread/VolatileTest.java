/**
 * volatile只能保证可见性，
 * 在不符合以下两条规则的运算场景中，我们仍然要通过加锁来保证原子性
 * （符合的时候，就不用加锁）
 * 
 * 运算结果并不依赖变量的当前值，或者能够确保只有单一的线程修改变量的值
 * 变量不需要与其他状态变量共同参与不变约束
 * 
 */
class VolatileTest{
	public static volatile int race = 0;
	// public static synchronized void increase(){
	public static void increase(){
		race++;
	}

	private static final int THREADS_COUNT=20;
	public static void main(String[] args)
	{
		Thread[] threads = new Thread[THREADS_COUNT];
		for(int i=0; i<THREADS_COUNT; ++i)
		{
			threads[i] = new Thread(new Runnable(){
				@Override
				public void run()
				{
					for(int i=0; i<10000; ++i)
					{
						increase();
					}
				}
			});
			threads[i].start();
		}

		while(Thread.activeCount() > 1)
			Thread.yield();
		System.out.println(race);
	}
}