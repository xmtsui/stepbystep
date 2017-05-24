import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class BackGroudThreadFailStop_fix1 {
	private static boolean stopRequested;
	private static synchronized void stopRequest(){
		stopRequested = true;
	}
	private static synchronized boolean whetherStop(){
		return stopRequested;
	}
	public static void main(String[] args){
		Thread bgThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while(!whetherStop()){
					i++;
					System.out.println("hehe");
				}
			}
		});
		bgThread.start();
		try{
			TimeUnit.SECONDS.sleep(1);
		}catch(java.lang.InterruptedException e){
			
		}
		stopRequest();
	}
}