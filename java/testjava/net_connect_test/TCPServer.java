import java.net.ServerSocket;  
import java.net.Socket;
import java.io.IOException;
class TCPServer{
	private Socket socket;
	private ServerSocket server;

	public TCPServer() throws InterruptedException, IOException {
		server = new ServerSocket(8888);
		while(true)
		{
			long start1 = System.currentTimeMillis();
			socket = server.accept();
			long end1 = System.currentTimeMillis();
			long time1 = end1 - start1;
			System.out.println( "accept time consume: " + time1);

			long start2 = System.currentTimeMillis();
			Thread.sleep(10);
			long end2 = System.currentTimeMillis();
			long time2 = end2 - start2;
			System.out.println( "consume: " + time2);
		}
	}

	public static void main(String[] args)
	{
		try {  
			new TCPServer();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}
}