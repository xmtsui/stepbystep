/**
 *
 */
public class TestWithNoThreadLocal {
	public static int a = 0;

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
		for ( int i = 0; i < 5; i++) {
			a = a + 1;
			System. out .println(Thread. currentThread ().getName() + ":" + a );
		}
	}
	public static class MyThread extends Thread {
		public void run() {
			for ( int i = 0; i < 5; i++) {
				a = a + 1;
				System. out .println(Thread. currentThread ().getName() + ":" + a );
			}
		}
	}

}