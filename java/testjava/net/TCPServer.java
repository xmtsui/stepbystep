import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.ServerSocket;  
import java.net.Socket;
class TCPServer{
	private Socket socket;
	private ServerSocket server;

	public TCPServer() throws IOException {
		server = new ServerSocket(8888);
		while(true)
		{
			socket = server.accept();
			BufferedReader br = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			System.out.println("Your input is : " + br.readLine()); 
		}
	}

	public static void main(String[] args)
	{
		try {  
			new TCPServer();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}
}