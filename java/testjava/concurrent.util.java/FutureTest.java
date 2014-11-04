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

class CalcCallable implements Callable{
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

class FutureTest{
	public static void main(String[] args)
	{
		Runnable aRunnable = new CalcRunnable();
		Thread aThread = new Thread(aRunnable);
		aThread.start();
		System.out.println("runnable end");

		Callable aCallable = new CalcCallable();
		FutureTask<String> aFuture = new FutureTask<String>(aCallable);
		Thread bThread = new Thread(aFuture);
		bThread.start();
		try{
			System.out.println("callable end, result: " + aFuture.get());
		}catch (InterruptedException e)
		{

		}catch (ExecutionException e)
		{

		}
	}
}