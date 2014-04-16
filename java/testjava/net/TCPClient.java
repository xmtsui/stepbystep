import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.Socket;  
import java.net.UnknownHostException; 
class TCPClient{
	private Socket client;  
	private PrintWriter pw;

	public TCPClient() throws UnknownHostException, IOException {  
		client = new Socket("localhost",8888);  
		System.out.println(client.getChannel());
		pw = new PrintWriter(client.getOutputStream());  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		pw.write(br.readLine());  
		pw.close();  
		br.close();  
	}

	public static void main(String[] args) {  
		try {  
			new TCPClient();  
		} catch (UnknownHostException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}  
}