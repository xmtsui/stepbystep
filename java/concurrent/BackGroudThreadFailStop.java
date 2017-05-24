import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class BackGroudThreadFailStop {
	private static boolean stopRequested;
	public static void main(String[] args){
		Thread bgThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while(!stopRequested){
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
		stopRequested = true;
	}
}