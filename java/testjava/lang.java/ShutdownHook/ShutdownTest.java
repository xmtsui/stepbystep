/** 
* This application is to demo how an applcation end 
*/
public class ShutdownTest { 
	
	public ShutdownTest() {
		//
	}
	
	public static void main(String[] args) {
		ShutdownTest shutdownTest1 = new ShutdownTest(); 
		System.out.println("hello world"); 
		//Do something before system exit 
		System.exit(0);
	}
}