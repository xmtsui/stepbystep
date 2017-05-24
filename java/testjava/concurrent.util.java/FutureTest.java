import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask; 
import java.util.concurrent.ExecutionException;
class CalcRunnable implements Runnable{
	final Random random = new Random(); 
	@Override
	public void run(){
		try{
			int randomInt = random.nextInt(10); 
			Thread.sleep(10);
			System.out.println("now runnable: " + randomInt);
		}catch(InterruptedException e)
		{

		}
	}
}

class CalcCallable implements Callable<String> {
	final Random random = new Random(); 
	@Override
	public String call(){
		try{
			int randomInt = random.nextInt(10); 
			Thread.sleep(1000);
			System.out.println("now callable: " + randomInt);
			return "call-" + randomInt;
			
		}catch(InterruptedException e)
		{
			return "call fail.";
		}
	}
}

class ExceptionCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		throw new Exception("Exception in callable!");
	}
}

class RuntimeExceptionCallable implements Callable<String> {
	@Override
	public String call() {
		throw new RuntimeException("Exception in callable!");
	}
}

class FutureTest{
	public static void main(String[] args) {
		Runnable aRunnable = new CalcRunnable();
		Thread aThread = new Thread(aRunnable);
		aThread.start();
		System.out.println("runnable end");
		testCallable(new CalcCallable());
		testCallable(new ExceptionCallable());
		testCallable(new RuntimeExceptionCallable());
	}

	private static void testCallable(Callable<String> callable) {
		FutureTask<String> future = new FutureTask<String>(callable);
		Thread thread = new Thread(future);
		thread.start();
		processResult(future);
	}

	private static void processResult(FutureTask future) {
		try{
			System.out.println("callable end, result: " + future.get()); 
		} catch (InterruptedException e) {
			System.err.println("Callable end, InterruptedException! type: [" + e.getClass().getName() + "], message: [" + e.getMessage() + "], case: [" + e.getCause().getClass().getName() + "].");
		} catch (ExecutionException e) {
			System.err.println("Callable end, ExecutionException! type: [" + e.getClass().getName() + "], message: [" + e.getMessage() + "], case: [" + e.getCause().getClass().getName() + "].");
		}
	}
}