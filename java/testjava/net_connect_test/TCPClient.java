import java.io.IOException;  
import java.net.Socket;  
class TCPClient{
	private Socket client;  

	public TCPClient() throws IOException {  
		long start1 = System.currentTimeMillis();
		client = new Socket("localhost",8888);  
		long end1 = System.currentTimeMillis();
		long time1 = end1 - start1;
		System.out.println( "client accept time consume: " + time1);
		
	}

	public static void main(String[] args) {  
		try {  
			new TCPClient();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}  
}