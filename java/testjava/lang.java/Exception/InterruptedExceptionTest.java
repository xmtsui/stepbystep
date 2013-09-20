class InterruptedExceptionTest extends Thread{
	public void run()
	{
		try{
			// while(true)
			Thread.sleep(1000);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws InterruptedException
	{
		Thread test = new InterruptedExceptionTest();
		while(true)
		{
			test.start();
			System.out.println("test: "+test.isInterrupted());
			// test.interrupt();
		 // test.join();
		 test.yield();
		 // test.suspend();
		 // test.wait();
		 // test.notify();
		}
	}
}